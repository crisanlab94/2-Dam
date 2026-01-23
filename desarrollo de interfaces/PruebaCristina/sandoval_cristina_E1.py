#Cristina Sandoval Laborde 2ºDAM
#Ejercicio 1
import pandas as pd
import datapane as dp

#Carga de fichero
fichero_csv="uso_servicios_municipales.csv"
df=pd.read_csv(fichero_csv)
df.columns=df.columns.str.lower()

#Lógica

filtro_municipio= df[(df['distrito']) & (df['anio'] == 2023)]
max_usosS=filtro_municipio['numero_usos'].idxmax()
max_municipio=filtro_municipio.loc[max_usosS]
nombre=max_municipio['distrito']
numero_total_servicios=max_municipio['numero_usos']

filtro_servicio=df[df['servicio']['anio']]
max_usos=filtro_servicio['numero_usos'].idxmax()
max_servicio = filtro_servicio.loc[max_usos]
nombre_servicio=max_servicio['servicio']
numero_total=max_servicio['servicio']



#Componentes
indicador_top_municipio=dp.BigNumber(headion=f"Top municipio 2023:{nombre}", value=f"{numero_total_servicios : , .2f}",)
indicador_top_servicio=dp.BigNumber(headion=f"Top servicio:{nombre_servicio}", value=f"{numero_total : , .2f}",)


#Tabla interactiva
tabla=dp.DataTable(df)

#Generacion
reporte = dp.Report(
    indicador_top_municipio,
    indicador_top_servicio,
    tabla

)

#Guardamos
reporte.save("sandoval_cristina_E1_tabla.html",open=True)

