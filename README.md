Proyecto donde conectamos dos microservicios entre sí (User y Post)
- User llama a post para poder ver los post relacionados con un user
- Post llama a user para que le de una lista de ids de todos lo usar para luego al crear un post que esté asignado a un user
Utilizamos openFeign y eureka server

Esto funcionalmente es para ver como conectarlo pero no está hecho de la mejor forma (hay que hacerlo con jwt y que con el token se infiera quiern esta creando el post y cosas así, pero sirve para
ver como funciona al menos)
