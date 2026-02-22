import pandas as pd
import datapane as dp

df=pd.read_csv('consorcio_transporte_uso_servicios.csv')

ultimo_anio=df['anio'].max()
top_distrito=df[df['anio']==ultimo_anio].groupby('distrito')['usos'].sum().idxmax()

top_servicio=df.groupby('servicio')['usos'].sum().idxmax()

df_b=df[(df['servicio'].str.startswith('B'))]
top_servicio_b=df_b.loc[df_b['usos'].idxmax(),['servicio']]

report=dp.Report(
    dp.Text("#Tabla interactiva y análisis de uso"),
    dp.DataTable(df),
    dp.Text(f"""
       ###Hallazgos:
       1.***Distrito({ultimo_anio}):** {top_distrito}
       2.***Servicio:**{top_servicio}
       3.***Servicios comienzan por B:**{top_servicio_b}
     """ )
)
report.save("sandoval_cristina_E1_tabla.html", open=True)

  