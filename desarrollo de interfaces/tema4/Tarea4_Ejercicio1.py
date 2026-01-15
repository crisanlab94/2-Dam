#Cristina Sandoval Laborde 
import pandas as pd
import datapane as dp

fichero_csv = "DI_U05_A02_02.csv"
df = pd.read_csv(fichero_csv)

#Los vendedores que en el mes de julio han vendido más de 500 unidades.
vendedores_julio = df[(df['Mes'] == 'Julio') & (df['Unidades'] > 500)]
total_vendedores = len(vendedores_julio)

indicador_julio = dp.BigNumber(
    heading='Ventas Julio > 500 uds',
    value=total_vendedores
)

# El vendedor con mayor importe de ventas en un mes, sin superar las 300 unidades, indicando también el mesen el que lo consiguió
filtro_unidades = df[df['Unidades'] < 300]
max_importe = filtro_unidades['Importe (€)'].idxmax()
max_vendedor = filtro_unidades.loc[max_importe]

nombre = max_vendedor['Nombre']
mes = max_vendedor['Mes']
importe = max_vendedor['Importe (€)']


indicador_max = dp.BigNumber(
    heading=f"Top: ({mes})",
    value=f"{importe} €"
)

#Vendedor cuyo nombre empieza por la letra S, quien vendió más unidades en el mes de agosto
vendedores_S_agosto = df[(df['Nombre'].str.startswith('S')) & (df['Mes'] == 'Agosto')]
idx_max_S = vendedores_S_agosto['Unidades'].idxmax()
vendedor_S_top = vendedores_S_agosto.loc[idx_max_S]

nombre_S = vendedor_S_top['Nombre']
unidades_S = vendedor_S_top['Unidades']

indicador_S = dp.BigNumber(
    heading=f"Top  Agosto: {nombre_S}",
    value=f"{unidades_S} Uds"
)

titulo = dp.HTML(
    '<p style="font-size:30px; text-align:center; color:#ffffff; background-color:#4d4d4d;">Ejercicio 1</p>'
)

report = dp.Report(
    titulo,
    dp.Text("# Informe de Ventas"),
    dp.Group(indicador_julio, indicador_max, indicador_S, columns=3),
    dp.DataTable(df)
)

report.save(path='Tarea4_Ejercicio1.html', open=True)