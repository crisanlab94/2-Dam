import pandas as pd
import datapane as dp

df=pd.read_csv('consorcio_tranporte_uso_servicios.csv')

total_usos=df['usos'].sum()

ultimo_anio=df['anio'].max()
uso_actual=df[df['anio']== ultimo_anio]['usos'].sum()
uso_anterior=df[df['anio']==(ultimo_anio -1)]['usos'].sum()
delta=uso_actual - uso_anterior

indicador_total=dp.BigNumber(
    heading="Total de usos acumulados", value=total_usos)

indicador_comparativo=dp.BigNumber(
    heading=f"Comparativa de uso en el último año {ultimo_anio} vs año anterior {ultimo_anio-1}",
    value=uso_actual,
    change=delta,
    is_upward_change=delta > 0
)

report=dp.Report(
    dp.Text("#Ejercicio 2. Indicadores y resumen ejecutivo"),
    dp.Text("Este informe explica el total de usos acumulados de transporte y la comparativa del año actual(último año) con el año anterior"),
    indicador_total,
    indicador_comparativo  
)
report.save("Sandoval_Cristina_E2_resumen.html", open=True)