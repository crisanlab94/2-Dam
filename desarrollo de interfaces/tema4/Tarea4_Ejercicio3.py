import pandas as pd
import datapane as dp

# CARGA DEL MODELO
df = pd.read_csv("DI_U05_A02_PP_E_01.csv")


df.columns = df.columns.str.lower()



# --- Gráfico 1: Tarta ---

ventas_producto = df.groupby(["tipo de producto"]).sum(numeric_only=True)
plot_pie = ventas_producto.plot.pie(
    y="ventas", 
    legend=False, 
    ylabel="", 
    title="Distribución por tipos de productos"
)
bloque_pie = dp.Plot(plot_pie)

# --- Gráfico 2: Líneas ---

ultimo_año = df["año"].max()
df_ultimos_2 = df[df["año"] >= (ultimo_año - 1)]
ventas_anuales = df_ultimos_2.groupby(["año"], sort=True).sum(numeric_only=True)
plot_line = ventas_anuales.plot(
    y="ventas", 
    kind="line", 
    marker="o", 
    title=f"Evolución de Ventas: {ultimo_año-1} - {ultimo_año}",
    xticks=ventas_anuales.index # Asegura que solo se vean los años como marcas
)
bloque_line = dp.Plot(plot_line)

# --- Gráfico 3: Barras (región) ---
ventas_pais = df.groupby(["región"]).sum(numeric_only=True).sort_values("ventas", ascending=False)
plot_bar = ventas_pais.plot.bar(
    y="ventas", 
    title="Análisis por Mercados (Países)"
)
bloque_bar = dp.Plot(plot_bar)

# CONSTRUCCIÓN DE LA VISTA (Informe Datapane)
reporte = dp.Report(
    dp.Text("# Informe Técnico de Ventas"),
    dp.Text("Basado en la organización de información del ERP."),
    
    dp.Text("## 1. Distribución de ventas totales"),
    bloque_pie,
    
    dp.Text("## 2. Tendencia Temporal"),
    bloque_line,
    
    dp.Text("## 3. Ventas por País"),
    bloque_bar,
    
    dp.Text("### Consulta de la tabla base"),
    dp.DataTable(df)
)

# ACCIÓN
reporte.save("informe_ventas_corregido.html")