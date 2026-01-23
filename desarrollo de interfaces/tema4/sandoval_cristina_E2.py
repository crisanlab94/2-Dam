#Cristina Sandoval Laborde 2ºDAM
#Ejercicio 2
import pandas as pd
import datapane as dp
import matplotlib.pyplot as plt

#Carga de fichero

df=pd.read_csv("uso_servicios_municipales.csv")
df.columns=df.columns.str.lower()

#Logica

filtro_municipio= df[(df['distrito']) & (df['anio'] == 2023)]
max_usosS=filtro_municipio['numero_usos'].idxmax()
max_municipio=filtro_municipio.loc[max_usosS]
nombre=max_municipio['distrito']
numero_total_servicios=max_municipio['numero_usos']



titulo=dp.HTML(
    '<p style = "font-size:30px; text-align:center'
)

reporte= dp.Report(
    titulo,
    dp.Text("Esta información es relevante para el ayuntamiento porque le permite ver de un solo vistazo el resumen ejecutivo de usos de servicios municipales"),
    total_usos,
    comparativa
)