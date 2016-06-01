var freeBus = {
    "type": "FeatureCollection",
    "features": [
        {
            "type": "Feature",
            "geometry": {
                "type": "LineString",
                "coordinates": [
                    [-105.00341892242432, 39.75383843460583],
                    [-105.0008225440979, 39.751891803969535]
                ]
            },
            "properties": {
                "popupContent": "This is free bus that will take you across downtown.",
                "underConstruction": false
            },
            "id": 1
        },
        {
            "type": "Feature",
            "geometry": {
                "type": "LineString",
                "coordinates": [
                    [-105.0008225440979, 39.751891803969535],
                    [-104.99820470809937, 39.74979664004068]
                ]
            },
            "properties": {
                "popupContent": "This is free bus that will take you across downtown.",
                "underConstruction": true
            },
            "id": 2
        },
        {
            "type": "Feature",
            "geometry": {
                "type": "LineString",
                "coordinates": [
                    [-104.99820470809937, 39.74979664004068],
                    [-104.98689651489258, 39.741052354709055]
                ]
            },
            "properties": {
                "popupContent": "This is free bus that will take you across downtown.",
                "underConstruction": false
            },
            "id": 3
        }
    ]
};

var lightRailStop = {
    "type": "FeatureCollection",
    "features": [
        {
            "type": "Feature",
            "properties": {
                "popupContent": "18th & California Light Rail Stop"
            },
            "geometry": {
                "type": "Point",
                "coordinates": [-104.98999178409576, 39.74683938093904]
            }
        },{
            "type": "Feature",
            "properties": {
                "popupContent": "20th & Welton Light Rail Stop"
            },
            "geometry": {
                "type": "Point",
                "coordinates": [-104.98689115047453, 39.747924136466565]
            }
        }
    ]
};

var bicycleRental = {
    "type": "FeatureCollection",
    "features": [
        {
            "geometry": {
                "type": "Point",
                "coordinates": [
                    -104.9998241,
                    39.7471494
                ]
            },
            "type": "Feature",
            "properties": {
                "popupContent": "This is a B-Cycle Station. Come pick up a bike and pay by the hour. What a deal!"
            },
            "id": 51
        },
        {
            "geometry": {
                "type": "Point",
                "coordinates": [
                    -104.9983545,
                    39.7502833
                ]
            },
            "type": "Feature",
            "properties": {
                "popupContent": "This is a B-Cycle Station. Come pick up a bike and pay by the hour. What a deal!"
            },
            "id": 52
        },
        {
            "geometry": {
                "type": "Point",
                "coordinates": [
                    -104.9963919,
                    39.7444271
                ]
            },
            "type": "Feature",
            "properties": {
                "popupContent": "This is a B-Cycle Station. Come pick up a bike and pay by the hour. What a deal!"
            },
            "id": 54
        },
        {
            "geometry": {
                "type": "Point",
                "coordinates": [
                    -104.9960754,
                    39.7498956
                ]
            },
            "type": "Feature",
            "properties": {
                "popupContent": "This is a B-Cycle Station. Come pick up a bike and pay by the hour. What a deal!"
            },
            "id": 55
        },
        {
            "geometry": {
                "type": "Point",
                "coordinates": [
                    -104.9933717,
                    39.7477264
                ]
            },
            "type": "Feature",
            "properties": {
                "popupContent": "This is a B-Cycle Station. Come pick up a bike and pay by the hour. What a deal!"
            },
            "id": 57
        },
        {
            "geometry": {
                "type": "Point",
                "coordinates": [
                    -104.9913392,
                    39.7432392
                ]
            },
            "type": "Feature",
            "properties": {
                "popupContent": "This is a B-Cycle Station. Come pick up a bike and pay by the hour. What a deal!"
            },
            "id": 58
        },
        {
            "geometry": {
                "type": "Point",
                "coordinates": [
                    -104.9788452,
                    39.6933755
                ]
            },
            "type": "Feature",
            "properties": {
                "popupContent": "This is a B-Cycle Station. Come pick up a bike and pay by the hour. What a deal!"
            },
            "id": 74
        }
    ]
};

var campus = {
    "type": "Feature",
    "properties": {
        "popupContent": "This is the Auraria West Campus",
        "style": {
            weight: 2,
            color: "#999",
            opacity: 1,
            fillColor: "#B0DE5C",
            fillOpacity: 0.8
        }
    },
    "geometry": {
        "type": "MultiPolygon",
        "coordinates": [
            [
                [
                    [-105.00432014465332, 39.74732195489861],
                    [-105.00715255737305, 39.74620006835170],
                    [-105.00921249389647, 39.74468219277038],
                    [-105.01067161560059, 39.74362625960105],
                    [-105.01195907592773, 39.74290029616054],
                    [-105.00989913940431, 39.74078835902781],
                    [-105.00758171081543, 39.74059036160317],
                    [-105.00346183776855, 39.74059036160317],
                    [-105.00097274780272, 39.74059036160317],
                    [-105.00062942504881, 39.74072235994946],
                    [-105.00020027160645, 39.74191033368865],
                    [-105.00071525573731, 39.74276830198601],
                    [-105.00097274780272, 39.74369225589818],
                    [-105.00097274780272, 39.74461619742136],
                    [-105.00123023986816, 39.74534214278395],
                    [-105.00183105468751, 39.74613407445653],
                    [-105.00432014465332, 39.74732195489861]
                ],[
                    [-105.00361204147337, 39.74354376414072],
                    [-105.00301122665405, 39.74278480127163],
                    [-105.00221729278564, 39.74316428375108],
                    [-105.00283956527711, 39.74390674342741],
                    [-105.00361204147337, 39.74354376414072]
                ]
            ],[
                [
                    [-105.00942707061768, 39.73989736613708],
                    [-105.00942707061768, 39.73910536278566],
                    [-105.00685214996338, 39.73923736397631],
                    [-105.00384807586671, 39.73910536278566],
                    [-105.00174522399902, 39.73903936209552],
                    [-105.00041484832764, 39.73910536278566],
                    [-105.00041484832764, 39.73979836621592],
                    [-105.00535011291504, 39.73986436617916],
                    [-105.00942707061768, 39.73989736613708]
                ]
            ]
        ]
    }
};

var events = {"features":[{"geometry":{"coordinates":[-1.4694537837778459,0.6043603960758324],"type":"Point"},"properties":{"prop0":"2015-04-01T08:04:20"}},{"geometry":{"coordinates":[-1.4694430396535405,0.604367967863135],"type":"Point"},"properties":{"prop0":"2015-04-01T08:15:06"}},{"geometry":{"coordinates":[-1.469442923231141,0.6043680499103659],"type":"Point"},"properties":{"prop0":"2015-04-01T08:15:13"}},{"geometry":{"coordinates":[-1.4694381499127576,0.60437141384683],"type":"Point"},"properties":{"prop0":"2015-04-01T08:20"}},{"geometry":{"coordinates":[-1.4028720795585659,0.6512830198530671],"type":"Point"},"properties":{"prop0":"2015-05-17T16:05:44"}},{"geometry":{"coordinates":[-1.40287176355491,0.6512832425526938],"type":"Point"},"properties":{"prop0":"2015-05-17T16:06:03"}},{"geometry":{"coordinates":[-1.4028716637642817,0.6512833128788915],"type":"Point"},"properties":{"prop0":"2015-05-17T16:06:09"}},{"geometry":{"coordinates":[-1.4028704330132002,0.6512841802353319],"type":"Point"},"properties":{"prop0":"2015-05-17T16:07:23"}},{"geometry":{"coordinates":[-1.4028700837460015,0.6512844263770243],"type":"Point"},"properties":{"prop0":"2015-05-17T16:07:44"}},{"geometry":{"coordinates":[-1.4028697677423454,0.6512846490766508],"type":"Point"},"properties":{"prop0":"2015-05-17T16:08:03"}},{"geometry":{"coordinates":[-1.4028694517386893,0.6512848717762774],"type":"Point"},"properties":{"prop0":"2015-05-17T16:08:22"}},{"geometry":{"coordinates":[-1.402868354041779,0.6512856453644539],"type":"Point"},"properties":{"prop0":"2015-05-17T16:09:28"}},{"geometry":{"coordinates":[-1.3386012601805921,0.696577076424452],"type":"Point"},"properties":{"prop0":"2015-07-01T09:31:24"}},{"geometry":{"coordinates":[-1.3386011770217352,0.6965771350296168],"type":"Point"},"properties":{"prop0":"2015-07-01T09:31:29"}},{"geometry":{"coordinates":[-1.3386010938628785,0.6965771936347818],"type":"Point"},"properties":{"prop0":"2015-07-01T09:31:34"}},{"geometry":{"coordinates":[-1.338600761227451,0.6965774280554412],"type":"Point"},"properties":{"prop0":"2015-07-01T09:31:54"}},{"geometry":{"coordinates":[-1.3386005450144232,0.69657758042887],"type":"Point"},"properties":{"prop0":"2015-07-01T09:32:07"}},{"geometry":{"coordinates":[-1.3386002789060814,0.6965777679653975],"type":"Point"},"properties":{"prop0":"2015-07-01T09:32:23"}},{"geometry":{"coordinates":[-1.24100393017516,0.7653575747791552],"type":"Point"},"properties":{"prop0":"2015-09-07T07:33:30"}},{"geometry":{"coordinates":[-1.2409965789322142,0.7653627554757306],"type":"Point"},"properties":{"prop0":"2015-09-07T07:40:52"}},{"geometry":{"coordinates":[-1.2409926372023994,0.765365533360546],"type":"Point"},"properties":{"prop0":"2015-09-07T07:44:49"}},{"geometry":{"coordinates":[-1.240992570675314,0.7653655802446779],"type":"Point"},"properties":{"prop0":"2015-09-07T07:44:53"}},{"geometry":{"coordinates":[-1.2409870655589903,0.7653694599065931],"type":"Point"},"properties":{"prop0":"2015-09-07T07:50:24"}}],"type":"FeatureCollection"};

var coorsField = {
    "type": "Feature",
    "properties": {
        "popupContent": "Coors Field"
    },
    "geometry": {
        "type": "Point",
        "coordinates": [-104.99404191970824, 39.756213909328125]
    }
};
