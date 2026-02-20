import pandas as pd
import datapane as dp
import matplotlib.pyplot as plt

df=pd.read_csv('consorcio_transporte_uso_servicios.csv')

#Sectores
servicios_usos= df.groupby('servicio')['usos'].sum()
fig1, ax1=plt.subplots() 
servicios_usos.plot.pie(ax=ax1,autopct='%1.1f%%',startangle=90)
ax1.set_title("Distribución de usos por servicio ")
ax1.set_ylabel("")


#Lineas
evolucion_anio=df.groupby('anio')['usos'].sum()
fig2,ax2=plt.subplots()
evolucion_anio.plot(kind='line',marker='o',color='green')
ax2.set_title("Evolución de usos totales por año ")
ax2.set_xlabel("Año")
ax2.set_ylabel("Total")
ax2.grid(True)

#Barras
distritos_usos= df.groupby('distrito')['usos'].sum().sort_values(ascending=False)
fig3,ax3=plt.subplots()
distritos_usos.plot(kind='bar', ax=ax3, color='skyblue')
ax3.set_title("Usos totales por distrito ")
ax3.set_xlabel("Distrito")
ax3.set_ylabel("Total")
plt.xticks(rotation=45)

report=dp.Report(
    dp.Text("#Graficos informativos"),
    dp.Text("#1.Distribución de usos por servicio"),
    dp.Plot(fig1),
    dp.Text("#2.Evolución de usos totales por año"),
    dp.Plot(fig2),
    dp.Text("#3.usos totales por distrito"),
    dp.Plot(fig3)
)
report.save("sandoval_cristina_E3_graficos.html")