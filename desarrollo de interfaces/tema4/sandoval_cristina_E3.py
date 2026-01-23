#Cristina Sandoval Laborde 2ºDAM
#Ejercicio 3
import pandas as pd
import datapane as dp
import matplotlib.pyplot as plt

#Carga de fichero

df=pd.read_csv("uso_servicios_municipales.csv")
df.columns=df.columns.str.lower()

#Elaboracion de graficos
plt.figure()


#Grafico de tarta
usos_servicio=df.groupby("servicio")['numero_usos'].sum()
plot1=usos_servicio.plot.pie(title="Distribución de usos por servicio", y="numero_usos", legend=False)

#Convertir a Datapone
bloque_pie=dp.Plot(plot1.get_figure())

#Grafico de lineas
usos_año=df.groupby("anio")['numero_usos'].sum()
plot2=usos_año.plot(kind="line",marker="o",title="Evolución de usos totales por año",color="blue")

#Convertir a Datapone
bloque_line=dp.Plot(plot2.get_figure())


#Grafico de barras

usos=df.groupby("distrito")['numero_usos'].sum()
plot3=usos.plot.bar(title="Usos totales por distrito", color="orange")

#Convertir a Datapone
bloque_bar=dp.Plot(plot3.get_figure())


#Genaracion
pagina1=dp.Page(
    title="Ejercicio3",
    blocks=[
        bloque_pie,
        bloque_line,
        bloque_bar
    ]
)
reporte = dp.Report(pagina1)


#Guardamos
reporte.save("sandoval_cristina_E3_graficos.html",open=True)

