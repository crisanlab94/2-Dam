import pandas as pd
import datapane as dp

# Carga de datos
fichero_csv = "DI_U05_A02_PP_E_01.csv"
df = pd.read_csv(fichero_csv)

# --- Cálculos para el Resumen Ejecutivo ---
total_acumulado = df['Ventas'].sum()
# Encontrar el año con más ventas 
ventas_por_anio = df.groupby('Año')['Ventas'].sum()
anio_top = ventas_por_anio.idxmax()

# --- Cálculos para la comparativa 2021 vs 2020 ---
ventas_2021 = df[df['Año'] == 2021]['Ventas'].sum()
ventas_2020 = df[df['Año'] == 2020]['Ventas'].sum()
diferencia_ventas = ventas_2021 - ventas_2020
es_positivo = ventas_2021 > ventas_2020

# --- Creación de Componentes ---

# 1. Logotipo de la empresa
imagen_logo = dp.Media(file='ejercicio2.png')

# 2. Título 
titulo_ej2 = dp.HTML(
    '<p style="font-size:30px; text-align:center; color:#ffffff; background-color:#4d4d4d;">Informe de ventas</p>'
)

# 3. Resumen ejecutivo
indicador_acumulado = dp.BigNumber(heading='Total Ventas Acumuladas', value=f"{total_acumulado:,.2f} €")
indicador_anio_max = dp.BigNumber(heading='Año con más ventas', value=anio_top)
texto_justificacion = dp.Text(
    "Estos datos permiten identificar tendencias de crecimiento y periodos de éxito comercial. "
    "Son esenciales para que la dirección tome decisiones sobre stock y personal basadas en el rendimiento real."
)

# 4. Ventas 2021 y comparación 
indicador_2021_total = dp.BigNumber(
    heading='Importe Total Ventas 2021',
    value=f"{ventas_2021:,.2f} €"
)
indicador_comparativa = dp.BigNumber(
    heading='Evolución 2021 vs 2020',
    value=f"{ventas_2021:,.2f} €", 
    change=f"{diferencia_ventas:,.2f} €", 
    is_upward_change=es_positivo
)

# 5. Tabla interactiva 
tabla_ventas = dp.DataTable(df)

# 6. Descarga del fichero 
fichero_adjunto = dp.Attachment(file=fichero_csv)

# --- Construcción del Informe  ---
report = dp.Report(
    imagen_logo,               
    titulo_ej2,                 
    
    dp.Text("## Resumen Ejecutivo"),
    dp.Group(indicador_acumulado, indicador_anio_max, columns=2),
    texto_justificacion,
    dp.Text("## Análisis Ventas 2021"),
    dp.Group(indicador_2021_total, indicador_comparativa, columns=2), 
    dp.Text("## Tabla Interactiva"),
    tabla_ventas,
    dp.Text("**Descargar datos originales:**"),
    fichero_adjunto
)

report.save(path='Tarea4_Ejercicio2.html', open=True)