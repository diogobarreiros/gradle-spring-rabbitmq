# gradle-spring-rabbitmq
Projeto demo que simula uma mensageria utilizando Spring e RabbitMQ

Segue abaixo algumas questões importantes de saber:

1 - O que é JMS? A JMS (Java Message Service) permite a comunicação assíncrona entre aplicações, utilizando dois modelos básicos de conectividade:

* Filas ponto-a-ponto (queues), onde mensagens submetidas por uma aplicação “produtora” são entregues a uma única aplicação “consumidora”. Pode haver vários consumidores conectados à mesma fila, neste caso somente um deles receberá cada mensagem;

* Canais publish/subscribe (topics), onde cada mensagem pode ser recebida simultaneamente por diversas aplicações consumidoras.

2 - O que é DLQ (Dead Letter Queue)? 
Fila de inatividade, que é usado para mensagens que são não entregues. O nome de fila de inatividade é especificado como uma propriedade do gerenciador de filas, e você também deve definir a fila. Esta é uma fila para fins especiais, no entanto, é apenas definida como uma fila local.

3 - O que é RabbitMQ? 
O RabbitMQ é uma implementação de um Message Broker com suporte ao protocolo AMQP escrito na linguagem de programação Erlang.
