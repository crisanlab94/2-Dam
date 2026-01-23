#Cristina Sandoval Laborde 2ºDAM
import pandas as pd
import datapane as dp
import matplotlib.pyplot as plt 

# 1. CARGA DEL MODELO 
df = pd.read_csv("DI_U05_A02_PP_E_01.csv")
df.columns = df.columns.str.lower()

# 2. CÁLCULO DE INDICADORES 
total_ventas = df["ventas"].sum()
media_ventas = df["ventas"].mean()

# 3. ELABORACIÓN DE GRÁFICOS 

# --- Gráfico 1: Sectores (PRODUCTO) s ---
plt.figure() 
ventas_prod = df.groupby("tipo de producto")["ventas"].sum()
plot1 = ventas_prod.plot.pie(title="Reparto por Producto", ylabel="")
bloque_pie = dp.Plot(plot1.get_figure(), label="Vista de Tarta")

# --- Gráfico 2: Barras (REGIÓN) ---
plt.figure()
ventas_reg = df.groupby("región")["ventas"].sum()
plot2 = ventas_reg.plot.bar(title="Ventas por Región", color="orange")
bloque_bar = dp.Plot(plot2.get_figure(), label="Vista de Barras")

# --- Gráfico 3: Líneas (AÑO) ---
plt.figure()
ventas_año = df.groupby("año")["ventas"].sum()
plot3 = ventas_año.plot(kind="line", marker="o", title="Evolución Temporal")
bloque_line = dp.Plot(plot3.get_figure(), label="Vista de Líneas")

# 4. ORGANIZACIÓN DEL INFORME 


pagina1 = dp.Page(
    title="Dashboard",
    
    blocks=[
        dp.Text("# Resumen de Operaciones"),
        dp.Group(
            dp.BigNumber(heading="Ventas Totales", value=f"{total_ventas:,.0f}€"),
            dp.BigNumber(heading="Venta Media", value=f"{media_ventas:,.2f}€"),
            columns=2
        ), 
        bloque_pie
    ]
)

# Página 2: Comparativas con Selectores 
pagina2 = dp.Page(
    title="Comparativas",
    blocks=[
        dp.Text("# Análisis Regional y de Tendencias"),
        dp.Select(blocks=[bloque_bar, bloque_line]) 
    ]
)

# Página 3: Explorador de Datos Maestros
pagina3 = dp.Page(
    title="Datos",
    blocks=[
        dp.Text("# Tabla Completa de Registros"),
        dp.DataTable(df)
    ]
)

# 5. GENERACIÓN FINAL
reporte = dp.Report(pagina1, pagina2, pagina3)
reporte.save("sandoval_cristina_T4.4.html", open=True)