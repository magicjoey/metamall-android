<?php
	$con=mysql_connect("localhost:3306","root","") or die(mysql_error);
	mysql_select_db("test2",$con) or die(mysql_error);
	mysql_query("set names 'gbk'");
?>