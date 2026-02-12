from cartera import Cartera  

### CONSTRUCTOR
def test_constructor_saldo_defecto():
    c = Cartera()
    assert c.saldo == 0 

def test_constructor_tipo_incorrecto():
   
    c = Cartera("dinero")
    assert c.saldo == 0 

def test_constructor_saldo_negativo():
  
    c = Cartera(-100)
    assert c.saldo == 0 

def test_constructor_saldo_valido():
   
    c = Cartera(50)
    assert c.saldo == 50 


### PRUEBAS DEL MÉTODO INGRESAR(CANTIDAD)

def test_ingresar_suma_correctamente():
   
    c = Cartera(50)
    nuevo_saldo = c.ingresar(50)
    assert nuevo_saldo == 100 
    assert c.saldo == 100 

def test_ingresar_no_valido():
    c = Cartera(50)
    resultado = c.ingresar(-10) 
    assert resultado is None 
    assert c.saldo == 50 


### PRUEBAS DEL MÉTODO GASTAR 

def test_gastar_resta_correctamente():
   
    c = Cartera(100)
    nuevo_saldo = c.gastar(40)
    assert nuevo_saldo == 60 
    assert c.saldo == 60 

def test_gastar_mas_del_disponible():
    c = Cartera(50)
    resultado = c.gastar(100) 
    assert resultado is None 
    assert c.saldo == 50 

def test_ingresar_tipo_incorrecto():
    c = Cartera(50)
    resultado = c.ingresar("mucho")
    assert resultado is None
    assert c.saldo == 50