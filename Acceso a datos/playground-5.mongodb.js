// 1. Seleccionamos la base de datos
use('cristinaSandoval');

db.createCollection("usuarios");

// 2. CREAMOS EL ÍNDICE 
db.getCollection('usuarios').createIndex(
  { "id": 1 },
  { unique: true }
);
db.getCollection('usuarios').insertMany([

  {
    "id": "usr001",
    "username": "User001",
    "email": "user001@fitdroid.com",
    "plan_activo": "MENSUAL",
    "dispositivo": "android14",
    "preferencias": {
      "tema_oscuro": true,
      "idioma": "ES",
      "notificaciones_push": false,
      "limite_datos_moviles": false
    },
    "eventosLogs": [
      {
        "id_evento": "ev_001",
        "tag": "LOGIN",
        "mensaje": "Inicio de sesión exitoso",
        "timestamp": "2024-02-10T08:03:00Z"
      },
      {
        "id_evento": "ev_002",
        "tag": "SYNC_ERROR",
        "mensaje": "Tiempo de espera agotado",
        "timestamp": "2024-02-10T08:03:00Z"
      },
      {
        "id_evento": "ev_003",
        "tag": "GPS",
        "mensaje": "Señal GPS adquirida",
        "timestamp": "2024-02-10T08:03:00Z"
      }
    ]
  },
  {
    "id": "usr002",
    "username": "User002",
    "email": "user002@fitdroid.com",
    "plan_activo": "MENSUAL",
    "dispositivo": "android11",
    "preferencias": {
      "tema_oscuro": false,
      "idioma": "ES",
      "notificaciones_push": true,
      "limite_datos_moviles": false
    },
    "eventosLogs": [
      {
        "id_evento": "ev_004",
        "tag": "LOGIN",
        "mensaje": "Inicio de sesión exitoso",
        "timestamp": "2024-02-10T08:06:00Z"
      },
      {
        "id_evento": "ev_005",
        "tag": "SYNC_ERROR",
        "mensaje": "Tiempo de espera agotado",
        "timestamp": "2024-02-10T08:06:00Z"
      },
      {
        "id_evento": "ev_006",
        "tag": "GPS",
        "mensaje": "Señal GPS adquirida",
        "timestamp": "2024-02-10T08:06:00Z"
      }
    ]
  },
  {
    "id": "usr003",
    "username": "User003",
    "email": "user003@fitdroid.com",
    "plan_activo": "TRIMESTRAL",
    "dispositivo": "android12",
    "preferencias": {
      "tema_oscuro": true,
      "idioma": "ES",
      "notificaciones_push": false,
      "limite_datos_moviles": true
    },
    "eventosLogs": [
      {
        "id_evento": "ev_007",
        "tag": "LOGIN",
        "mensaje": "Inicio de sesión exitoso",
        "timestamp": "2024-02-11T08:09:00Z"
      },
      {
        "id_evento": "ev_008",
        "tag": "SYNC_ERROR",
        "mensaje": "Tiempo de espera agotado",
        "timestamp": "2024-02-11T08:09:00Z"
      },
      {
        "id_evento": "ev_009",
        "tag": "GPS",
        "mensaje": "Señal GPS adquirida",
        "timestamp": "2024-02-11T08:09:00Z"
      }
    ]
  },
  {
    "id": "usr004",
    "username": "User004",
    "email": "user004@fitdroid.com",
    "plan_activo": "ANUAL",
    "dispositivo": "iphone16",
    "preferencias": {
      "tema_oscuro": false,
      "idioma": "ES",
      "notificaciones_push": true,
      "limite_datos_moviles": false
    },
    "eventosLogs": [
      {
        "id_evento": "ev_010",
        "tag": "LOGIN",
        "mensaje": "Inicio de sesión exitoso",
        "timestamp": "2024-02-11T08:12:00Z"
      },
      {
        "id_evento": "ev_011",
        "tag": "SYNC_ERROR",
        "mensaje": "Tiempo de espera agotado",
        "timestamp": "2024-02-11T08:12:00Z"
      },
      {
        "id_evento": "ev_012",
        "tag": "GPS",
        "mensaje": "Señal GPS adquirida",
        "timestamp": "2024-02-11T08:12:00Z"
      }
    ]
  },
  {
    "id": "usr005",
    "username": "User005",
    "email": "user005@fitdroid.com",
    "plan_activo": "MENSUAL",
    "dispositivo": "android14",
    "preferencias": {
      "tema_oscuro": true,
      "idioma": "ES",
      "notificaciones_push": true,
      "limite_datos_moviles": false
    },
    "eventosLogs": [
      {
        "id_evento": "ev_013",
        "tag": "LOGIN",
        "mensaje": "Inicio de sesión exitoso",
        "timestamp": "2024-02-11T08:15:00Z"
      },
      {
        "id_evento": "ev_014",
        "tag": "SYNC_ERROR",
        "mensaje": "Tiempo de espera agotado",
        "timestamp": "2024-02-11T08:15:00Z"
      },
      {
        "id_evento": "ev_015",
        "tag": "GPS",
        "mensaje": "Señal GPS adquirida",
        "timestamp": "2024-02-11T08:15:00Z"
      }
    ]
  },
  {
    "id": "usr006",
    "username": "User006",
    "email": "user006@fitdroid.com",
    "plan_activo": "FREE",
    "dispositivo": "android13",
    "preferencias": {
      "tema_oscuro": false,
      "idioma": "ES",
      "notificaciones_push": true,
      "limite_datos_moviles": true
    },
    "eventosLogs": [
      {
        "id_evento": "ev_016",
        "tag": "LOGIN",
        "mensaje": "Inicio de sesión exitoso",
        "timestamp": "2024-02-12T08:18:00Z"
      },
      {
        "id_evento": "ev_017",
        "tag": "SYNC_ERROR",
        "mensaje": "Tiempo de espera agotado",
        "timestamp": "2024-02-12T08:18:00Z"
      },
      {
        "id_evento": "ev_018",
        "tag": "GPS",
        "mensaje": "Señal GPS adquirida",
        "timestamp": "2024-02-12T08:18:00Z"
      }
    ]
  },
  {
    "id": "usr007",
    "username": "User007",
    "email": "user007@fitdroid.com",
    "plan_activo": "MENSUAL",
    "dispositivo": "android11",
    "preferencias": {
      "tema_oscuro": true,
      "idioma": "ES",
      "notificaciones_push": false,
      "limite_datos_moviles": false
    },
    "eventosLogs": [
      {
        "id_evento": "ev_019",
        "tag": "LOGIN",
        "mensaje": "Inicio de sesión exitoso",
        "timestamp": "2024-02-12T08:21:00Z"
      },
      {
        "id_evento": "ev_020",
        "tag": "SYNC_ERROR",
        "mensaje": "Tiempo de espera agotado",
        "timestamp": "2024-02-12T08:21:00Z"
      },
      {
        "id_evento": "ev_021",
        "tag": "GPS",
        "mensaje": "Señal GPS adquirida",
        "timestamp": "2024-02-12T08:21:00Z"
      }
    ]
  },
  {
    "id": "usr008",
    "username": "User008",
    "email": "user008@fitdroid.com",
    "plan_activo": "TRIMESTRAL",
    "dispositivo": "android11",
    "preferencias": {
      "tema_oscuro": false,
      "idioma": "ES",
      "notificaciones_push": true,
      "limite_datos_moviles": false
    },
    "eventosLogs": [
      {
        "id_evento": "ev_022",
        "tag": "LOGIN",
        "mensaje": "Inicio de sesión exitoso",
        "timestamp": "2024-02-12T08:24:00Z"
      },
      {
        "id_evento": "ev_023",
        "tag": "SYNC_ERROR",
        "mensaje": "Tiempo de espera agotado",
        "timestamp": "2024-02-12T08:24:00Z"
      },
      {
        "id_evento": "ev_024",
        "tag": "GPS",
        "mensaje": "Señal GPS adquirida",
        "timestamp": "2024-02-12T08:24:00Z"
      }
    ]
  },
  {
    "id": "usr009",
    "username": "User009",
    "email": "user009@fitdroid.com",
    "plan_activo": "ANUAL",
    "dispositivo": "android12",
    "preferencias": {
      "tema_oscuro": true,
      "idioma": "ES",
      "notificaciones_push": false,
      "limite_datos_moviles": true
    },
    "eventosLogs": [
      {
        "id_evento": "ev_025",
        "tag": "LOGIN",
        "mensaje": "Inicio de sesión exitoso",
        "timestamp": "2024-02-13T08:27:00Z"
      },
      {
        "id_evento": "ev_026",
        "tag": "SYNC_ERROR",
        "mensaje": "Tiempo de espera agotado",
        "timestamp": "2024-02-13T08:27:00Z"
      },
      {
        "id_evento": "ev_027",
        "tag": "GPS",
        "mensaje": "Señal GPS adquirida",
        "timestamp": "2024-02-13T08:27:00Z"
      }
    ]
  },
  {
    "id": "usr010",
    "username": "User010",
    "email": "user010@fitdroid.com",
    "plan_activo": "VIP",
    "dispositivo": "android13",
    "preferencias": {
      "tema_oscuro": false,
      "idioma": "ES",
      "notificaciones_push": true,
      "limite_datos_moviles": false
    },
    "eventosLogs": [
      {
        "id_evento": "ev_028",
        "tag": "LOGIN",
        "mensaje": "Inicio de sesión exitoso",
        "timestamp": "2024-02-13T08:30:00Z"
      },
      {
        "id_evento": "ev_029",
        "tag": "SYNC_ERROR",
        "mensaje": "Tiempo de espera agotado",
        "timestamp": "2024-02-13T08:30:00Z"
      },
      {
        "id_evento": "ev_030",
        "tag": "GPS",
        "mensaje": "Señal GPS adquirida",
        "timestamp": "2024-02-13T08:30:00Z"
      }
    ]
  },
  {
    "id": "usr011",
    "username": "User011",
    "email": "user011@fitdroid.com",
    "plan_activo": "FREE",
    "dispositivo": "android13",
    "preferencias": {
      "tema_oscuro": true,
      "idioma": "ES",
      "notificaciones_push": false,
      "limite_datos_moviles": false
    },
    "eventosLogs": [
      {
        "id_evento": "ev_031",
        "tag": "LOGIN",
        "mensaje": "Inicio de sesión exitoso",
        "timestamp": "2024-02-13T08:33:00Z"
      },
      {
        "id_evento": "ev_032",
        "tag": "SYNC_ERROR",
        "mensaje": "Tiempo de espera agotado",
        "timestamp": "2024-02-13T08:33:00Z"
      },
      {
        "id_evento": "ev_033",
        "tag": "GPS",
        "mensaje": "Señal GPS adquirida",
        "timestamp": "2024-02-13T08:33:00Z"
      }
    ]
  },
  {
    "id": "usr012",
    "username": "User012",
    "email": "user012@fitdroid.com",
    "plan_activo": "MENSUAL",
    "dispositivo": "iphone17",
    "preferencias": {
      "tema_oscuro": false,
      "idioma": "ES",
      "notificaciones_push": true,
      "limite_datos_moviles": true
    },
    "eventosLogs": [
      {
        "id_evento": "ev_034",
        "tag": "LOGIN",
        "mensaje": "Inicio de sesión exitoso",
        "timestamp": "2024-02-14T08:36:00Z"
      },
      {
        "id_evento": "ev_035",
        "tag": "SYNC_ERROR",
        "mensaje": "Tiempo de espera agotado",
        "timestamp": "2024-02-14T08:36:00Z"
      },
      {
        "id_evento": "ev_036",
        "tag": "GPS",
        "mensaje": "Señal GPS adquirida",
        "timestamp": "2024-02-14T08:36:00Z"
      }
    ]
  },
  {
    "id": "usr013",
    "username": "User013",
    "email": "user013@fitdroid.com",
    "plan_activo": "TRIMESTRAL",
    "dispositivo": "android12",
    "preferencias": {
      "tema_oscuro": true,
      "idioma": "ES",
      "notificaciones_push": false,
      "limite_datos_moviles": false
    },
    "eventosLogs": [
      {
        "id_evento": "ev_037",
        "tag": "LOGIN",
        "mensaje": "Inicio de sesión exitoso",
        "timestamp": "2024-02-14T08:39:00Z"
      },
      {
        "id_evento": "ev_038",
        "tag": "SYNC_ERROR",
        "mensaje": "Tiempo de espera agotado",
        "timestamp": "2024-02-14T08:39:00Z"
      },
      {
        "id_evento": "ev_039",
        "tag": "GPS",
        "mensaje": "Señal GPS adquirida",
        "timestamp": "2024-02-14T08:39:00Z"
      }
    ]
  },
  {
    "id": "usr014",
    "username": "User014",
    "email": "user014@fitdroid.com",
    "plan_activo": "ANUAL",
    "dispositivo": "android11",
    "preferencias": {
      "tema_oscuro": false,
      "idioma": "ES",
      "notificaciones_push": true,
      "limite_datos_moviles": false
    },
    "eventosLogs": [
      {
        "id_evento": "ev_040",
        "tag": "LOGIN",
        "mensaje": "Inicio de sesión exitoso",
        "timestamp": "2024-02-14T08:42:00Z"
      },
      {
        "id_evento": "ev_041",
        "tag": "SYNC_ERROR",
        "mensaje": "Tiempo de espera agotado",
        "timestamp": "2024-02-14T08:42:00Z"
      },
      {
        "id_evento": "ev_042",
        "tag": "GPS",
        "mensaje": "Señal GPS adquirida",
        "timestamp": "2024-02-14T08:42:00Z"
      }
    ]
  },
  {
    "id": "usr015",
    "username": "User015",
    "email": "user015@fitdroid.com",
    "plan_activo": "VIP",
    "dispositivo": "android13",
    "preferencias": {
      "tema_oscuro": true,
      "idioma": "ES",
      "notificaciones_push": false,
      "limite_datos_moviles": true
    },
    "eventosLogs": [
      {
        "id_evento": "ev_043",
        "tag": "LOGIN",
        "mensaje": "Inicio de sesión exitoso",
        "timestamp": "2024-02-15T08:45:00Z"
      },
      {
        "id_evento": "ev_044",
        "tag": "SYNC_ERROR",
        "mensaje": "Tiempo de espera agotado",
        "timestamp": "2024-02-15T08:45:00Z"
      },
      {
        "id_evento": "ev_045",
        "tag": "GPS",
        "mensaje": "Señal GPS adquirida",
        "timestamp": "2024-02-15T08:45:00Z"
      }
    ]
  },
  {
    "id": "usr016",
    "username": "User016",
    "email": "user016@fitdroid.com",
    "plan_activo": "FREE",
    "dispositivo": "iphone16",
    "preferencias": {
      "tema_oscuro": false,
      "idioma": "ES",
      "notificaciones_push": true,
      "limite_datos_moviles": false
    },
    "eventosLogs": [
      {
        "id_evento": "ev_046",
        "tag": "LOGIN",
        "mensaje": "Inicio de sesión exitoso",
        "timestamp": "2024-02-15T08:48:00Z"
      },
      {
        "id_evento": "ev_047",
        "tag": "SYNC_ERROR",
        "mensaje": "Tiempo de espera agotado",
        "timestamp": "2024-02-15T08:48:00Z"
      },
      {
        "id_evento": "ev_048",
        "tag": "GPS",
        "mensaje": "Señal GPS adquirida",
        "timestamp": "2024-02-15T08:48:00Z"
      }
    ]
  },
  {
    "id": "usr017",
    "username": "User017",
    "email": "user017@fitdroid.com",
    "plan_activo": "MENSUAL",
    "dispositivo": "android13",
    "preferencias": {
      "tema_oscuro": true,
      "idioma": "ES",
      "notificaciones_push": false,
      "limite_datos_moviles": false
    },
    "eventosLogs": [
      {
        "id_evento": "ev_049",
        "tag": "LOGIN",
        "mensaje": "Inicio de sesión exitoso",
        "timestamp": "2024-02-15T08:51:00Z"
      },
      {
        "id_evento": "ev_050",
        "tag": "SYNC_ERROR",
        "mensaje": "Tiempo de espera agotado",
        "timestamp": "2024-02-15T08:51:00Z"
      },
      {
        "id_evento": "ev_051",
        "tag": "GPS",
        "mensaje": "Señal GPS adquirida",
        "timestamp": "2024-02-15T08:51:00Z"
      }
    ]
  },
  {
    "id": "usr018",
    "username": "User018",
    "email": "user018@fitdroid.com",
    "plan_activo": "TRIMESTRAL",
    "dispositivo": "iphone16",
    "preferencias": {
      "tema_oscuro": false,
      "idioma": "ES",
      "notificaciones_push": true,
      "limite_datos_moviles": true
    },
    "eventosLogs": [
      {
        "id_evento": "ev_052",
        "tag": "LOGIN",
        "mensaje": "Inicio de sesión exitoso",
        "timestamp": "2024-02-16T08:54:00Z"
      },
      {
        "id_evento": "ev_053",
        "tag": "SYNC_ERROR",
        "mensaje": "Tiempo de espera agotado",
        "timestamp": "2024-02-16T08:54:00Z"
      },
      {
        "id_evento": "ev_054",
        "tag": "GPS",
        "mensaje": "Señal GPS adquirida",
        "timestamp": "2024-02-16T08:54:00Z"
      }
    ]
  },
  {
    "id": "usr019",
    "username": "User019",
    "email": "user019@fitdroid.com",
    "plan_activo": "ANUAL",
    "dispositivo": "android11",
    "preferencias": {
      "tema_oscuro": true,
      "idioma": "ES",
      "notificaciones_push": false,
      "limite_datos_moviles": false
    },
    "eventosLogs": [
      {
        "id_evento": "ev_055",
        "tag": "LOGIN",
        "mensaje": "Inicio de sesión exitoso",
        "timestamp": "2024-02-16T08:57:00Z"
      },
      {
        "id_evento": "ev_056",
        "tag": "SYNC_ERROR",
        "mensaje": "Tiempo de espera agotado",
        "timestamp": "2024-02-16T08:57:00Z"
      },
      {
        "id_evento": "ev_057",
        "tag": "GPS",
        "mensaje": "Señal GPS adquirida",
        "timestamp": "2024-02-16T08:57:00Z"
      }
    ]
  },
  {
    "id": "usr020",
    "username": "User020",
    "email": "user020@fitdroid.com",
    "plan_activo": "VIP",
    "dispositivo": "iphone17",
    "preferencias": {
      "tema_oscuro": false,
      "idioma": "ES",
      "notificaciones_push": true,
      "limite_datos_moviles": false
    },
    "eventosLogs": [
      {
        "id_evento": "ev_058",
        "tag": "LOGIN",
        "mensaje": "Inicio de sesión exitoso",
        "timestamp": "2024-02-16T08:00:00Z"
      },
      {
        "id_evento": "ev_059",
        "tag": "SYNC_ERROR",
        "mensaje": "Tiempo de espera agotado",
        "timestamp": "2024-02-16T08:00:00Z"
      },
      {
        "id_evento": "ev_060",
        "tag": "GPS",
        "mensaje": "Señal GPS adquirida",
        "timestamp": "2024-02-16T08:00:00Z"
      }
    ]
  },
  {
    "id": "usr021",
    "username": "User021",
    "email": "user021@fitdroid.com",
    "plan_activo": "FREE",
    "dispositivo": "android11",
    "preferencias": {
      "tema_oscuro": true,
      "idioma": "ES",
      "notificaciones_push": false,
      "limite_datos_moviles": true
    },
    "eventosLogs": [
      {
        "id_evento": "ev_061",
        "tag": "LOGIN",
        "mensaje": "Inicio de sesión exitoso",
        "timestamp": "2024-02-17T08:03:00Z"
      },
      {
        "id_evento": "ev_062",
        "tag": "SYNC_ERROR",
        "mensaje": "Tiempo de espera agotado",
        "timestamp": "2024-02-17T08:03:00Z"
      },
      {
        "id_evento": "ev_063",
        "tag": "GPS",
        "mensaje": "Señal GPS adquirida",
        "timestamp": "2024-02-17T08:03:00Z"
      }
    ]
  },
  {
    "id": "usr022",
    "username": "User022",
    "email": "user022@fitdroid.com",
    "plan_activo": "MENSUAL",
    "dispositivo": "iphone17",
    "preferencias": {
      "tema_oscuro": false,
      "idioma": "ES",
      "notificaciones_push": true,
      "limite_datos_moviles": false
    },
    "eventosLogs": [
      {
        "id_evento": "ev_064",
        "tag": "LOGIN",
        "mensaje": "Inicio de sesión exitoso",
        "timestamp": "2024-02-17T08:06:00Z"
      },
      {
        "id_evento": "ev_065",
        "tag": "SYNC_ERROR",
        "mensaje": "Tiempo de espera agotado",
        "timestamp": "2024-02-17T08:06:00Z"
      },
      {
        "id_evento": "ev_066",
        "tag": "GPS",
        "mensaje": "Señal GPS adquirida",
        "timestamp": "2024-02-17T08:06:00Z"
      }
    ]
  },
  {
    "id": "usr023",
    "username": "User023",
    "email": "user023@fitdroid.com",
    "plan_activo": "MENSUAL",
    "dispositivo": "android14",
    "preferencias": {
      "tema_oscuro": true,
      "idioma": "ES",
      "notificaciones_push": true,
      "limite_datos_moviles": false
    },
    "eventosLogs": [
      {
        "id_evento": "ev_067",
        "tag": "LOGIN",
        "mensaje": "Inicio de sesión exitoso",
        "timestamp": "2024-02-17T08:09:00Z"
      },
      {
        "id_evento": "ev_068",
        "tag": "SYNC_ERROR",
        "mensaje": "Tiempo de espera agotado",
        "timestamp": "2024-02-17T08:09:00Z"
      },
      {
        "id_evento": "ev_069",
        "tag": "GPS",
        "mensaje": "Señal GPS adquirida",
        "timestamp": "2024-02-17T08:09:00Z"
      }
    ]
  },
  {
    "id": "usr024",
    "username": "User024",
    "email": "user024@fitdroid.com",
    "plan_activo": "ANUAL",
    "dispositivo": "android13",
    "preferencias": {
      "tema_oscuro": false,
      "idioma": "ES",
      "notificaciones_push": true,
      "limite_datos_moviles": true
    },
    "eventosLogs": [
      {
        "id_evento": "ev_070",
        "tag": "LOGIN",
        "mensaje": "Inicio de sesión exitoso",
        "timestamp": "2024-02-18T08:12:00Z"
      },
      {
        "id_evento": "ev_071",
        "tag": "SYNC_ERROR",
        "mensaje": "Tiempo de espera agotado",
        "timestamp": "2024-02-18T08:12:00Z"
      },
      {
        "id_evento": "ev_072",
        "tag": "GPS",
        "mensaje": "Señal GPS adquirida",
        "timestamp": "2024-02-18T08:12:00Z"
      }
    ]
  },
  {
    "id": "usr025",
    "username": "User025",
    "email": "user025@fitdroid.com",
    "plan_activo": "VIP",
    "dispositivo": "iphone15",
    "preferencias": {
      "tema_oscuro": true,
      "idioma": "ES",
      "notificaciones_push": false,
      "limite_datos_moviles": false
    },
    "eventosLogs": [
      {
        "id_evento": "ev_073",
        "tag": "LOGIN",
        "mensaje": "Inicio de sesión exitoso",
        "timestamp": "2024-02-18T08:15:00Z"
      },
      {
        "id_evento": "ev_074",
        "tag": "SYNC_ERROR",
        "mensaje": "Tiempo de espera agotado",
        "timestamp": "2024-02-18T08:15:00Z"
      },
      {
        "id_evento": "ev_075",
        "tag": "GPS",
        "mensaje": "Señal GPS adquirida",
        "timestamp": "2024-02-18T08:15:00Z"
      }
    ]
  },
  {
    "id": "usr026",
    "username": "User026",
    "email": "user026@fitdroid.com",
    "plan_activo": "FREE",
    "dispositivo": "iphone14",
    "preferencias": {
      "tema_oscuro": false,
      "idioma": "ES",
      "notificaciones_push": true,
      "limite_datos_moviles": false
    },
    "eventosLogs": [
      {
        "id_evento": "ev_076",
        "tag": "LOGIN",
        "mensaje": "Inicio de sesión exitoso",
        "timestamp": "2024-02-18T08:18:00Z"
      },
      {
        "id_evento": "ev_077",
        "tag": "SYNC_ERROR",
        "mensaje": "Tiempo de espera agotado",
        "timestamp": "2024-02-18T08:18:00Z"
      },
      {
        "id_evento": "ev_078",
        "tag": "GPS",
        "mensaje": "Señal GPS adquirida",
        "timestamp": "2024-02-18T08:18:00Z"
      }
    ]
  },
  {
    "id": "usr027",
    "username": "User027",
    "email": "user027@fitdroid.com",
    "plan_activo": "MENSUAL",
    "dispositivo": "iphone17",
    "preferencias": {
      "tema_oscuro": true,
      "idioma": "ES",
      "notificaciones_push": false,
      "limite_datos_moviles": true
    },
    "eventosLogs": [
      {
        "id_evento": "ev_079",
        "tag": "LOGIN",
        "mensaje": "Inicio de sesión exitoso",
        "timestamp": "2024-02-19T08:21:00Z"
      },
      {
        "id_evento": "ev_080",
        "tag": "SYNC_ERROR",
        "mensaje": "Tiempo de espera agotado",
        "timestamp": "2024-02-19T08:21:00Z"
      },
      {
        "id_evento": "ev_081",
        "tag": "GPS",
        "mensaje": "Señal GPS adquirida",
        "timestamp": "2024-02-19T08:21:00Z"
      }
    ]
  },
  {
    "id": "usr028",
    "username": "User028",
    "email": "user028@fitdroid.com",
    "plan_activo": "TRIMESTRAL",
    "dispositivo": "android13",
    "preferencias": {
      "tema_oscuro": false,
      "idioma": "ES",
      "notificaciones_push": true,
      "limite_datos_moviles": false
    },
    "eventosLogs": [
      {
        "id_evento": "ev_082",
        "tag": "LOGIN",
        "mensaje": "Inicio de sesión exitoso",
        "timestamp": "2024-02-19T08:24:00Z"
      },
      {
        "id_evento": "ev_083",
        "tag": "SYNC_ERROR",
        "mensaje": "Tiempo de espera agotado",
        "timestamp": "2024-02-19T08:24:00Z"
      },
      {
        "id_evento": "ev_084",
        "tag": "GPS",
        "mensaje": "Señal GPS adquirida",
        "timestamp": "2024-02-19T08:24:00Z"
      }
    ]
  },
  {
    "id": "usr029",
    "username": "User029",
    "email": "user029@fitdroid.com",
    "plan_activo": "ANUAL",
    "dispositivo": "android14",
    "preferencias": {
      "tema_oscuro": true,
      "idioma": "ES",
      "notificaciones_push": false,
      "limite_datos_moviles": false
    },
    "eventosLogs": [
      {
        "id_evento": "ev_085",
        "tag": "LOGIN",
        "mensaje": "Inicio de sesión exitoso",
        "timestamp": "2024-02-19T08:27:00Z"
      },
      {
        "id_evento": "ev_086",
        "tag": "SYNC_ERROR",
        "mensaje": "Tiempo de espera agotado",
        "timestamp": "2024-02-19T08:27:00Z"
      },
      {
        "id_evento": "ev_087",
        "tag": "GPS",
        "mensaje": "Señal GPS adquirida",
        "timestamp": "2024-02-19T08:27:00Z"
      }
    ]
  },
  {
    "id": "usr030",
    "username": "User030",
    "email": "user030@fitdroid.com",
    "plan_activo": "VIP",
    "dispositivo": "android11",
    "preferencias": {
      "tema_oscuro": false,
      "idioma": "ES",
      "notificaciones_push": true,
      "limite_datos_moviles": true
    },
    "eventosLogs": [
      {
        "id_evento": "ev_088",
        "tag": "LOGIN",
        "mensaje": "Inicio de sesión exitoso",
        "timestamp": "2024-02-20T08:30:00Z"
      },
      {
        "id_evento": "ev_089",
        "tag": "SYNC_ERROR",
        "mensaje": "Tiempo de espera agotado",
        "timestamp": "2024-02-20T08:30:00Z"
      },
      {
        "id_evento": "ev_090",
        "tag": "GPS",
        "mensaje": "Señal GPS adquirida",
        "timestamp": "2024-02-20T08:30:00Z"
      }
    ]
  },
  {
    "id": "usr031",
    "username": "User031",
    "email": "user031@fitdroid.com",
    "plan_activo": "FREE",
    "dispositivo": "iphone15",
    "preferencias": {
      "tema_oscuro": true,
      "idioma": "ES",
      "notificaciones_push": false,
      "limite_datos_moviles": false
    },
    "eventosLogs": [
      {
        "id_evento": "ev_091",
        "tag": "LOGIN",
        "mensaje": "Inicio de sesión exitoso",
        "timestamp": "2024-02-20T08:33:00Z"
      },
      {
        "id_evento": "ev_092",
        "tag": "SYNC_ERROR",
        "mensaje": "Tiempo de espera agotado",
        "timestamp": "2024-02-20T08:33:00Z"
      },
      {
        "id_evento": "ev_093",
        "tag": "GPS",
        "mensaje": "Señal GPS adquirida",
        "timestamp": "2024-02-20T08:33:00Z"
      }
    ]
  },
  {
    "id": "usr032",
    "username": "User032",
    "email": "user032@fitdroid.com",
    "plan_activo": "MENSUAL",
    "dispositivo": "android12",
    "preferencias": {
      "tema_oscuro": false,
      "idioma": "ES",
      "notificaciones_push": true,
      "limite_datos_moviles": false
    },
    "eventosLogs": [
      {
        "id_evento": "ev_094",
        "tag": "LOGIN",
        "mensaje": "Inicio de sesión exitoso",
        "timestamp": "2024-02-20T08:36:00Z"
      },
      {
        "id_evento": "ev_095",
        "tag": "SYNC_ERROR",
        "mensaje": "Tiempo de espera agotado",
        "timestamp": "2024-02-20T08:36:00Z"
      },
      {
        "id_evento": "ev_096",
        "tag": "GPS",
        "mensaje": "Señal GPS adquirida",
        "timestamp": "2024-02-20T08:36:00Z"
      }
    ]
  },
  {
    "id": "usr033",
    "username": "User033",
    "email": "user033@fitdroid.com",
    "plan_activo": "TRIMESTRAL",
    "dispositivo": "android14",
    "preferencias": {
      "tema_oscuro": true,
      "idioma": "ES",
      "notificaciones_push": true,
      "limite_datos_moviles": true
    },
    "eventosLogs": [
      {
        "id_evento": "ev_097",
        "tag": "LOGIN",
        "mensaje": "Inicio de sesión exitoso",
        "timestamp": "2024-02-21T08:39:00Z"
      },
      {
        "id_evento": "ev_098",
        "tag": "SYNC_ERROR",
        "mensaje": "Tiempo de espera agotado",
        "timestamp": "2024-02-21T08:39:00Z"
      },
      {
        "id_evento": "ev_099",
        "tag": "GPS",
        "mensaje": "Señal GPS adquirida",
        "timestamp": "2024-02-21T08:39:00Z"
      }
    ]
  },
  {
    "id": "usr034",
    "username": "User034",
    "email": "user034@fitdroid.com",
    "plan_activo": "ANUAL",
    "dispositivo": "iphone15",
    "preferencias": {
      "tema_oscuro": false,
      "idioma": "ES",
      "notificaciones_push": true,
      "limite_datos_moviles": false
    },
    "eventosLogs": [
      {
        "id_evento": "ev_100",
        "tag": "LOGIN",
        "mensaje": "Inicio de sesión exitoso",
        "timestamp": "2024-02-21T08:42:00Z"
      },
      {
        "id_evento": "ev_101",
        "tag": "SYNC_ERROR",
        "mensaje": "Tiempo de espera agotado",
        "timestamp": "2024-02-21T08:42:00Z"
      },
      {
        "id_evento": "ev_102",
        "tag": "GPS",
        "mensaje": "Señal GPS adquirida",
        "timestamp": "2024-02-21T08:42:00Z"
      }
    ]
  },
  {
    "id": "usr035",
    "username": "User035",
    "email": "user035@fitdroid.com",
    "plan_activo": "VIP",
    "dispositivo": "iphone17",
    "preferencias": {
      "tema_oscuro": true,
      "idioma": "ES",
      "notificaciones_push": false,
      "limite_datos_moviles": false
    },
    "eventosLogs": [
      {
        "id_evento": "ev_103",
        "tag": "LOGIN",
        "mensaje": "Inicio de sesión exitoso",
        "timestamp": "2024-02-21T08:45:00Z"
      },
      {
        "id_evento": "ev_104",
        "tag": "SYNC_ERROR",
        "mensaje": "Tiempo de espera agotado",
        "timestamp": "2024-02-21T08:45:00Z"
      },
      {
        "id_evento": "ev_105",
        "tag": "GPS",
        "mensaje": "Señal GPS adquirida",
        "timestamp": "2024-02-21T08:45:00Z"
      }
    ]
  },
  {
    "id": "usr036",
    "username": "User036",
    "email": "user036@fitdroid.com",
    "plan_activo": "FREE",
    "dispositivo": "android13",
    "preferencias": {
      "tema_oscuro": false,
      "idioma": "ES",
      "notificaciones_push": true,
      "limite_datos_moviles": true
    },
    "eventosLogs": [
      {
        "id_evento": "ev_106",
        "tag": "LOGIN",
        "mensaje": "Inicio de sesión exitoso",
        "timestamp": "2024-02-22T08:48:00Z"
      },
      {
        "id_evento": "ev_107",
        "tag": "SYNC_ERROR",
        "mensaje": "Tiempo de espera agotado",
        "timestamp": "2024-02-22T08:48:00Z"
      },
      {
        "id_evento": "ev_108",
        "tag": "GPS",
        "mensaje": "Señal GPS adquirida",
        "timestamp": "2024-02-22T08:48:00Z"
      }
    ]
  },
  {
    "id": "usr037",
    "username": "User037",
    "email": "user037@fitdroid.com",
    "plan_activo": "MENSUAL",
    "dispositivo": "android13",
    "preferencias": {
      "tema_oscuro": true,
      "idioma": "ES",
      "notificaciones_push": false,
      "limite_datos_moviles": false
    },
    "eventosLogs": [
      {
        "id_evento": "ev_109",
        "tag": "LOGIN",
        "mensaje": "Inicio de sesión exitoso",
        "timestamp": "2024-02-22T08:51:00Z"
      },
      {
        "id_evento": "ev_110",
        "tag": "SYNC_ERROR",
        "mensaje": "Tiempo de espera agotado",
        "timestamp": "2024-02-22T08:51:00Z"
      },
      {
        "id_evento": "ev_111",
        "tag": "GPS",
        "mensaje": "Señal GPS adquirida",
        "timestamp": "2024-02-22T08:51:00Z"
      }
    ]
  },
  {
    "id": "usr038",
    "username": "User038",
    "email": "user038@fitdroid.com",
    "plan_activo": "TRIMESTRAL",
    "dispositivo": "iphone14",
    "preferencias": {
      "tema_oscuro": false,
      "idioma": "ES",
      "notificaciones_push": true,
      "limite_datos_moviles": false
    },
    "eventosLogs": [
      {
        "id_evento": "ev_112",
        "tag": "LOGIN",
        "mensaje": "Inicio de sesión exitoso",
        "timestamp": "2024-02-22T08:54:00Z"
      },
      {
        "id_evento": "ev_113",
        "tag": "SYNC_ERROR",
        "mensaje": "Tiempo de espera agotado",
        "timestamp": "2024-02-22T08:54:00Z"
      },
      {
        "id_evento": "ev_114",
        "tag": "GPS",
        "mensaje": "Señal GPS adquirida",
        "timestamp": "2024-02-22T08:54:00Z"
      }
    ]
  },
  {
    "id": "usr039",
    "username": "User039",
    "email": "user039@fitdroid.com",
    "plan_activo": "ANUAL",
    "dispositivo": "android14",
    "preferencias": {
      "tema_oscuro": true,
      "idioma": "ES",
      "notificaciones_push": false,
      "limite_datos_moviles": true
    },
    "eventosLogs": [
      {
        "id_evento": "ev_115",
        "tag": "LOGIN",
        "mensaje": "Inicio de sesión exitoso",
        "timestamp": "2024-02-23T08:57:00Z"
      },
      {
        "id_evento": "ev_116",
        "tag": "SYNC_ERROR",
        "mensaje": "Tiempo de espera agotado",
        "timestamp": "2024-02-23T08:57:00Z"
      },
      {
        "id_evento": "ev_117",
        "tag": "GPS",
        "mensaje": "Señal GPS adquirida",
        "timestamp": "2024-02-23T08:57:00Z"
      }
    ]
  },
  {
    "id": "usr040",
    "username": "User040",
    "email": "user040@fitdroid.com",
    "plan_activo": "VIP",
    "dispositivo": "android14",
    "preferencias": {
      "tema_oscuro": false,
      "idioma": "ES",
      "notificaciones_push": true,
      "limite_datos_moviles": false
    },
    "eventosLogs": [
      {
        "id_evento": "ev_118",
        "tag": "LOGIN",
        "mensaje": "Inicio de sesión exitoso",
        "timestamp": "2024-02-23T08:00:00Z"
      },
      {
        "id_evento": "ev_119",
        "tag": "SYNC_ERROR",
        "mensaje": "Tiempo de espera agotado",
        "timestamp": "2024-02-23T08:00:00Z"
      },
      {
        "id_evento": "ev_120",
        "tag": "GPS",
        "mensaje": "Señal GPS adquirida",
        "timestamp": "2024-02-23T08:00:00Z"
      }
    ]
  }
]);