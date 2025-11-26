//Crear base de datos
use("peliculas");

//Crear coleccion
db.createCollection("estudiantes");

db.estudiantes.insertMany(
   [
      {
         "_id": 0,
         "name": "aimee Zank",
         "address": {
            "city": "NEW YORK",
            "zip": 10030,
            "street": "FREDERICK DOUGLASS BLVD",
            "number": 2655
         },
         "scores": [
            {
               "score": 1.463179736705023,
               "type": "exam"
            },
            {
               "score": 11.78273309957772,
               "type": "quiz"
            },
            {
               "score": 35.8740349954354,
               "type": "homework"
            }
         ],
         "notaMedia": 10.194156002515204,
         "aficiones": [
            "lectura",
            "videojuegos"
         ]
      },
      {
         "_id": 1,
         "name": "Aurelia Menendez",
         "address": {
            "city": "QUEENS VLG",
            "zip": 11427,
            "street": "214TH ST",
            "number": 8823
         },
         "scores": [
            {
               "score": 60.06045071030959,
               "type": "exam"
            },
            {
               "score": 52.79790691903873,
               "type": "quiz"
            },
            {
               "score": 71.76133439165544,
               "type": "homework"
            }
         ],
         "notaMedia": 3.688513139480233,
         "aficiones": [
            "natación",
            "padel"
         ]
      },
      {
         "_id": 2,
         "name": "Corliss Zuk",
         "address": {
            "city": "WADING RIVER",
            "zip": 11792,
            "street": "WADING RIVER MANOR RD",
            "number": 1607
         },
         "scores": [
            {
               "score": 67.03077096065002,
               "type": "exam"
            },
            {
               "score": 6.301851677835235,
               "type": "quiz"
            },
            {
               "score": 66.28344683278382,
               "type": "homework"
            }
         ],
         "notaMedia": 9.411835994815792,
         "aficiones": [
            "padel",
            "lectura"
         ]
      },
      {
         "_id": 3,
         "name": "Bao Ziglar",
         "address": {
            "city": "RIDGEWOOD",
            "zip": 11385,
            "street": "MENAHAN ST",
            "number": 1712
         },
         "scores": [
            {
               "score": 71.64343899778332,
               "type": "exam"
            },
            {
               "score": 24.80221293650313,
               "type": "quiz"
            },
            {
               "score": 42.26147058804812,
               "type": "homework"
            }
         ],
         "notaMedia": 10.59320711937816,
         "aficiones": [
            "futbol",
            "tenis"
         ]
      },
      {
         "_id": 4,
         "name": "Zachary Langlais",
         "address": {
            "city": "RIDGEWOOD",
            "zip": 11385,
            "street": "MENAHAN ST",
            "number": 1712
         },
         "scores": [
            {
               "score": 78.68385091304332,
               "type": "exam"
            },
            {
               "score": 90.2963101368042,
               "type": "quiz"
            },
            {
               "score": 34.41620148042529,
               "type": "homework"
            }
         ],
         "notaMedia": 3.6843707564989683,
         "aficiones": [
            "videojuegos",
            "senderismo"
         ]
      },
      {
         "_id": 5,
         "name": "Wilburn Spiess",
         "address": {
            "city": "BROOKLYN",
            "zip": 11222,
            "street": "NEWELL ST",
            "number": 51
         },
         "scores": [
            {
               "score": 44.87186330181261,
               "type": "exam"
            },
            {
               "score": 25.72395114668016,
               "type": "quiz"
            },
            {
               "score": 63.42288310628662,
               "type": "homework"
            }
         ],
         "notaMedia": 10.807521450503177,
         "aficiones": [
            "tenis",
            "baloncesto"
         ]
      },
      {
         "_id": 6,
         "name": "Jenette Flanders",
         "address": {
            "city": "BROOKLYN",
            "zip": 11218,
            "street": "DORCHESTER RD",
            "number": 1107
         },
         "scores": [
            {
               "score": 37.32285459166097,
               "type": "exam"
            },
            {
               "score": 28.32634976913737,
               "type": "quiz"
            },
            {
               "score": 81.57115318686338,
               "type": "homework"
            }
         ],
         "notaMedia": 10.450900919544775,
         "aficiones": [
            "padel",
            "lectura"
         ]
      },
      {
         "_id": 7,
         "name": "Salena Olmos",
         "address": {
            "city": "WADING RIVER",
            "zip": 11792,
            "street": "WADING RIVER MANOR RD",
            "number": 1607
         },
         "scores": [
            {
               "score": 90.37826509157176,
               "type": "exam"
            },
            {
               "score": 42.48780666956811,
               "type": "quiz"
            },
            {
               "score": 96.52986171633331,
               "type": "homework"
            }
         ],
         "notaMedia": 4.396591376806411,
         "aficiones": [
            "lectura",
            "videojuegos"
         ]
      },
      {
         "_id": 8,
         "name": "Daphne Zheng",
         "address": {
            "city": "BROOKLYN",
            "zip": 11235,
            "street": "OCEAN PKWY",
            "number": 3100
         },
         "scores": [
            {
               "score": 22.13583712862635,
               "type": "exam"
            },
            {
               "score": 14.63969941335069,
               "type": "quiz"
            },
            {
               "score": 75.94123677556644,
               "type": "homework"
            }
         ],
         "notaMedia": 5.5777127994124145,
         "aficiones": [
            "padel",
            "lectura"
         ]
      },
      {
         "_id": 9,
         "name": "Sanda Ryba",
         "address": {
            "city": "ASTORIA",
            "zip": 11106,
            "street": "35TH ST",
            "number": 3520
         },
         "scores": [
            {
               "score": 97.00509953654694,
               "type": "exam"
            },
            {
               "score": 97.80449632538915,
               "type": "quiz"
            },
            {
               "score": 25.27368532432955,
               "type": "homework"
            }
         ],
         "notaMedia": 3.6914243732798693,
         "aficiones": [
            "natación",
            "padel"
         ]
      },
      {
         "_id": 10,
         "name": "Denisha Cast",
         "address": {
            "city": "MANHTTAN",
            "zip": 10012,
            "street": "",
            "number": "N/W/C"
         },
         "scores": [
            {
               "score": 45.61876862259409,
               "type": "exam"
            },
            {
               "score": 98.35723209418343,
               "type": "quiz"
            },
            {
               "score": 55.90835657173456,
               "type": "homework"
            }
         ],
         "notaMedia": 7.433828572669336,
         "aficiones": [
            "videojuegos",
            "senderismo"
         ]
      },
      {
         "_id": 11,
         "name": "Marcus Blohm",
         "address": {
            "city": "NEW HYDE PARK",
            "zip": 11040,
            "street": "FALMOUTH AVE",
            "number": 1818
         },
         "scores": [
            {
               "score": 78.42617835651868,
               "type": "exam"
            },
            {
               "score": 82.58372817930675,
               "type": "quiz"
            },
            {
               "score": 87.49924733328717,
               "type": "homework"
            }
         ],
         "notaMedia": 2.025537224827251,
         "aficiones": [
            "videojuegos",
            "senderismo"
         ]
      },
      {
         "_id": 12,
         "name": "Quincy Danaher",
         "address": {
            "city": "RIDGEWOOD",
            "zip": 11385,
            "street": "WOODWARD AVE",
            "number": 610
         },
         "scores": [
            {
               "score": 54.29841278520669,
               "type": "exam"
            },
            {
               "score": 85.61270164694737,
               "type": "quiz"
            },
            {
               "score": 80.40732356118075,
               "type": "homework"
            }
         ],
         "notaMedia": 2.672136281751231,
         "aficiones": [
            "senderismo",
            "surf"
         ]
      },
      {
         "_id": 13,
         "name": "Jessika Dagenais",
         "address": {
            "city": "RIDGEWOOD",
            "zip": 11385,
            "street": "WOODWARD AVE",
            "number": 610
         },
         "scores": [
            {
               "score": 90.47179954427436,
               "type": "exam"
            },
            {
               "score": 90.3001402468489,
               "type": "quiz"
            },
            {
               "score": 95.17753772405909,
               "type": "homework"
            }
         ],
         "notaMedia": 3.388144795197358,
         "aficiones": [
            "lectura",
            "videojuegos"
         ]
      },
      {
         "_id": 14,
         "name": "Alix Sherrill",
         "address": {
            "city": "RIDGEWOOD",
            "zip": 11385,
            "street": "WOODWARD AVE",
            "number": 610
         },
         "scores": [
            {
               "score": 25.15924151998215,
               "type": "exam"
            },
            {
               "score": 68.64484047692098,
               "type": "quiz"
            },
            {
               "score": 24.68462152686763,
               "type": "homework"
            }
         ],
         "notaMedia": 1.9395397448887488,
         "aficiones": [
            "natación",
            "padel"
         ]
      },
      {
         "_id": 15,
         "name": "Tambra Mercure",
         "address": {
            "city": "NEW YORK",
            "zip": 10027,
            "street": "W 126TH ST",
            "number": 361
         },
         "scores": [
            {
               "score": 69.1565022533158,
               "type": "exam"
            },
            {
               "score": 3.311794422000724,
               "type": "quiz"
            },
            {
               "score": 45.03178973642521,
               "type": "homework"
            }
         ],
         "notaMedia": 1.3550007018423273,
         "aficiones": [
            "baloncesto",
            "atletismo"
         ]
      },
      {
         "_id": 16,
         "name": "Dodie Staller",
         "address": {
            "city": "NEW ROCHELLE",
            "zip": 10801,
            "street": "MAIN ST",
            "number": 212
         },
         "scores": [
            {
               "score": 7.772386442858281,
               "type": "exam"
            },
            {
               "score": 31.84300235104542,
               "type": "quiz"
            },
            {
               "score": 80.52136407989194,
               "type": "homework"
            }
         ],
         "notaMedia": 10.984540857866552,
         "aficiones": [
            "lectura",
            "videojuegos"
         ]
      },
      {
         "_id": 17,
         "name": "Fletcher Mcconnell",
         "address": {
            "city": "BRONX",
            "zip": 10452,
            "street": "WALTON AVE",
            "number": 1155
         },
         "scores": [
            {
               "score": 39.41011069729274,
               "type": "exam"
            },
            {
               "score": 81.13270307809924,
               "type": "quiz"
            },
            {
               "score": 97.70116640402922,
               "type": "homework"
            }
         ],
         "notaMedia": 1.551406110497299,
         "aficiones": [
            "atletismo",
            "natación"
         ]
      },
      {
         "_id": 18,
         "name": "Verdell Sowinski",
         "address": {
            "city": "BRONX",
            "zip": 10461,
            "street": "HALPERIN AVE",
            "number": 2617
         },
         "scores": [
            {
               "score": 62.12870233109035,
               "type": "exam"
            },
            {
               "score": 84.74586220889356,
               "type": "quiz"
            },
            {
               "score": 81.58947824932574,
               "type": "homework"
            }
         ],
         "notaMedia": 1.8780621364179186,
         "aficiones": [
            "futbol",
            "tenis"
         ]
      },
      {
         "_id": 19,
         "name": "Gisela Levin",
         "address": {
            "city": "BRONX",
            "zip": 10461,
            "street": "HALPERIN AVE",
            "number": 2617
         },
         "scores": [
            {
               "score": 44.51211101958831,
               "type": "exam"
            },
            {
               "score": 0.6578497966368002,
               "type": "quiz"
            },
            {
               "score": 93.36341655949683,
               "type": "homework"
            }
         ],
         "notaMedia": 4.8963669345128675,
         "aficiones": [
            "atletismo",
            "natación"
         ]
      }
   ]
);


db.estudiantes.insertOne(
   {
      "movie": "The Haunting",
      "year": 1999,
      "release_date": "1999-07-23",
      "director": "Jan de Bont",
      "character": "Luke Sanderson",
      "movie_duration": "01:52:37",
      "poster": "https://images.ctfassets.net/bs8ntwkklfua/6Zu7ux0JYrUWk0UC8vxPtj/d0da8bfbad670655779a9921ac514759/The_Haunting_Poster.jpg",
      "video":
      {
         "1080p": "https://videos.ctfassets.net/bs8ntwkklfua/01C128tlGJ79vtlFEPc7V9/184759a9ca6fb1ab57114977afccb2ff/The_Haunting_Wow_4_1080p.mp4",
         "360p": "https://videos.ctfassets.net/bs8ntwkklfua/6bwt57tdhBCbhsXZ4i0pce/7f6debc7e52f80c78a618f19c8087985/The_Haunting_Wow_4_360p.mp4"
      }
   }


)
/*
//Ver contenido de mi coleccion equivalente a select *
db.empleados.find();

//ver el contenido de uno en concreto  .count() saber cantidad
db.empleados.find({'cargo': 'Analista de Datos'}).count();

//ver solo cierto contenido
db.empleados.find({},{'nombre':1, 'cargo': 1, '_id':0})

//asi no sale el id
db.empleados.find({_id:'12345'});

//para hacer una busqueda por id generado por la base de datos
db.empleados.find({_id: ObjectId("691c4db0d7061cd9efb77df4")});

//
*/

db.estudiantes.findOneAndDelete({ _id: 10 })

db.estudiantes.find()

