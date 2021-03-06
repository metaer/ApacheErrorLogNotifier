ApacheErrorLogNotifier
======================
Эта программа предназначена для уведомления админа о всех ошибках, которые попали в лог апача.
В частости очень удобно её использовать совместно с php фрейморком Symfony 2.
Как известно, в симфони есть и свой инструмент для уведомлений об ошибках, но он сообщает не обо всех ошибках, а лишь о выброшенных исключениях, в том числе не пойманных.
Как его настроить - описано здесь:
http://symfony.com/doc/current/cookbook/logging/monolog_email.html
Он прекрасно работает, и его можно использовать.
Проверить его можно, написав в своем контроллере throw new InternalErrorException();

Далее, в prod версии отсутствует перехватчик php ошибок через функцию set_error_handler. В app_dev.php это строчка
Debug::enable();
Если мы посмотрим на app.php, то не увидим её там.

Попробуй написать где-нибудь в своем контроллере $a = 5/0;
php сгенерирует warning, но prod версия симфонии просто проглотит его и пойдет выполняться дальше.
Представь, если у тебя где-нибудь в расчетах используется $a = $b/$c. И ты забыл сделать проверку на $c неравно 0.
В итоге результат расчета будет неверным. И ты можешь даже не подозревать об этом, пока кто-нибудь из пользователей не заметит.

Ещё одна ситуация: фатальные ошибки php, например при require "path/to/file.php";
Если такой файл не существует, то симфония, как и при всех других фатальных ошибках, выдаст либо белый экран, либо то, что успело к моменту фатальной ошибки попасть в поток вывода.
И опять же, пока пользователи не сообщат об этом баге, ты не будешь подозревать, что он существует в твоей программе, что довольно-таки нехорошо.

Все эти проблемы решает программа ApacheErrorLogNotifier
Она работает очень просто: обо всех новых записях, попавших в лог апача, она уведомляет админа по емэилу.
Просто добавь в расписание крона запуск этой программы, например, раз в минуту. И в течение минуты после возникновения php'шной ошибки, ты будешь уведомлен об этом через электронную почту.

P.S. Если в файле расписания крона указать свой email в параметре MAILTO, то можно ещё и получать на почту ошибки при отработке ApacheErrorLogNotifier, если таковые возникли.
Так что если что-то случится с ApacheErrorLogNotifier, и ты перестанешь получать php ошибки на почту, то крон-демон уведомит тебя об этом!

Getting started:

1. cd /var
2. mkdir programs
3. cd programs
4. git clone https://github.com/metaer/ApacheErrorLogNotifier
4.1. cd ApacheErrorLogNotifier
5. mkdir database && mkdir logs
6. sudo add-apt-repository ppa:webupd8team/java
7. sudo apt-get update
8. sudo apt-get install oracle-java7-installer
9. sudo apt-get install oracle-java7-set-default
10. sudo apt-get install ant
11. В папку /var/programs/ApacheErrorLogNotifier/lib нужно залить библиотеку javax.mail-1.4.7.jar
11. ant
12. java -classpath /var/programs/ApacheErrorLogNotifier/lib/javax.mail-1.4.7.jar:/var/programs/ApacheErrorLogNotifier/out/production/ApacheErrorLogNotifier/ ru.pavelpopovjava.Main path/to/project_error.log user@example.com /var/programs/ApacheErrorLogNotifier/database/data.txt /var/programs/ApacheErrorLogNotifier/logs/ theme from@example.com
13. Добавить в крон

Все параметры в п.12 поменять на свои.

Список параметров:

- Файл лога ошибок апача
- Емэил куда слать ошибки
- Файл с датой-временем последней обработанной записи
- Директория для логов выполнения данной программы
- Тема письма
- От кого письмо

Делал на скорую руку работоспособную версию, поэтому какого-либо оформленного окончательного варианта программы не получилось, но пользоваться можно и этим. Отлично работает.

