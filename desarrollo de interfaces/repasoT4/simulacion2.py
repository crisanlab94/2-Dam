import pandas as pd
import datapane as dp
df =pd.read_csv('movilidad_urbana.csv')
total_acumulados=df['pasajeros'].sum()
total_pasajeros=dp.BigNumber(
    heading="Total pasajeros", value= total_acumulados)

ultimo_anio=df['anio'].max()
anio_actual= df[df['anio'] == ultimo_anio]['pasajeros'].sum()
anio_anterior=df[df['anio'] == (ultimo_anio - 1)]['pasajeros'].sum()
delta=anio_actual - anio_anterior

comparacion_anios=dp.BigNumber(
    heading=f"Comparativa ultimo año {ultimo_anio} vs {ultimo_anio - 1}", value=anio_actual,
    change=delta,
    is_upward_change=delta >0
)
report=dp.Report(
    dp.Text("# Balance Movilidad Urbana"),
    dp.Text("Este informe es un aprueba que estoy haciendo a ver si consigo mañana aprobar el examen"),
    total_pasajeros,
    comparacion_anios
)
report.save("apellido1_nombre.hmtl",open=True)