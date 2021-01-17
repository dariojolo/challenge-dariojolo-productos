# challenge-sysone-productos
La solución propuesta cuenta con dos servicios, el servicio servicio-productos, es el encargado de manejar los vehiculos que salen de fabrica y que estan disponibles para la venta en los puntos de venta.

&nbsp;
Para cumplir con el proposito expone una API Rest con los siguientes métodos y se ha deplyado en la nube con el servicio de ***Heroku***

&nbsp;
Si lo ejecutamos localmente el servicio va a escuchar en el puerto :8081

&nbsp;
Cuando el Servicio levanta ejecuta un script SQL para poblar la base de datos (H2) utilizada para la solucion con los vehiculos disponibles para la venta segun el relevamiento

&nbsp;
***Obtener un listado de los productos disponibles***

&nbsp;
Para obtener un listado de los productos disponibles se debe llamar al siguiente método con el verbo GET
```
https://servicio-productos.herokuapp.com/api/products
```
Y obtenemos como respuesta un listado de todos los productos que disponemos para la venta
```
[
    {
        "id": 1,
        "modelo": "Sedan",
        "precioMinimo": 1.0,
        "precioMaximo": 100000.0
    },
    {
        "id": 2,
        "modelo": "Coupe",
        "precioMinimo": 1.0,
        "precioMaximo": 100000.0
    },
    {
        "id": 3,
        "modelo": "Familiar",
        "precioMinimo": 1.0,
        "precioMaximo": 100000.0
    }
]
```
***Visualizar un producto***

&nbsp;
Para visualizar un producto debemos llamar al siguiente método con el verbo GET pasandole como parametro el ID el producto
```
https://servicio-productos.herokuapp.com/api/products/1
```
Y obtendremos como respuesta un objeto JSON con el registro a visualizar
```
{
   "id": 1,
   "modelo": "Sedan",
   "precioMinimo": 1.0,
   "precioMaximo": 100000.0
}
```
***Crear un producto nuevo para la ventas***

&nbsp;
Para crear un nuevo producto para la venta debemos llamar al siguiente método con el verbo POST
```
https://servicio-productos.herokuapp.com/api/products
```
Debemos pasarle como parametro un objeto JSON con el siguiente formato
```
 {
    "modelo": "SUV",
    "precioMinimo": 1000.0,
    "precioMaximo": 1000000.0
 }
```
Y obtendremos como respuesta un objeto JSON con el registro creado
```
{
    "id": 4,
    "modelo": "SUV",
    "precioMinimo": 1000.0,
    "precioMaximo": 1000000.0
}
```
***Actualizar un producto***

&nbsp;
Para actualiozar un producto debemos llamar al siguiente método con el verbo PUT y pasandole como parametro el ID del vehiculo a modificar
```
https://servicio-productos.herokuapp.com/api/products/4
```
y un objeto con los nuevos valores, teniendo en cuenta que solo se puede modificar el precio minimo y el precio maxima (actualizacion parcial)

```
{
   "precioMinimo": 2000.0,
   "precioMaximo": 2000000.0
}
```
Y obtendremos como resultado el objeto modificado
```
{
    "id": 4,
    "modelo": "SUV",
    "precioMinimo": 2000.0,
    "precioMaximo": 2000000.0
}
```
***Eliminar un producto***

&nbsp;
Para eliminar un producto, debemos llamar al siguiente método con el verbo DELETE Y pasando como parametro el ID del producto a eliminar
```
https://servicio-productos.herokuapp.com/api/products/3
```
