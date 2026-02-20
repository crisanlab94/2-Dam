import pandas as pd
import datapane as dp
import matplotlib.pyplot as plt

df=pd.read_csv('consorcio_transporte_uso_servicios.csv')

total_usos=df['usos'].sum()

ultimo_anio=df['anio'].max()
uso_actual=df[df['anio']== ultimo_anio]['usos'].sum()
uso_anterior=df[df['anio']==(ultimo_anio -1)]['usos'].sum()
delta=uso_actual - uso_anterior

fig_linea,ax_1=plt.subplots()
df.groupby('anio')['usos'].sum().plot(kind='line', marker='o',ax=ax_1)
ax_1.set_title("Tendencia")




report=dp.Report(
    dp.Page(
        title="Resumen informativo",
        blocks=[
            dp.Text("#Resumen"),
            dp.Text("Este informe es un resumen generalizado del uso del consorcio de transportes"),
            dp.Group(
                dp.BigNumber(heading="Total de usos acumulados", value=total_usos),
                dp.BigNumber(
                    heading=f"Comparativa de uso en el último año {ultimo_anio} vs año anterior {ultimo_anio-1}",
                    value=uso_actual,
                    change=delta,
                    is_upward_change=delta > 0
                ),
            columns=2
            )
        ]
    ),

      dp.Page(title="Análisis",
                    blocks=[
                        dp.Text("#Analisis"),
                        dp.Select(
                            blocks=[
                                dp.DataTable(df,label="Explorar"),
                                dp.Plot(fig_linea,label="ver")
                            ]
                        )

                    ]
                    )

)
report.save("sandoval_cristina_E4_informe_organizado.html", open=True)