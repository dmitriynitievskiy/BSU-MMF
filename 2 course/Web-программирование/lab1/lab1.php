//Среди n чисел найдите наибольшее и наименьшее простые числа. 
<?php
$array = array(29,3,7,2,11,10,13,5,12,27,43,25,24,19,3);
$k = 0;
$pr = 0;
$res = array();
foreach ($array as $value){
	for ($i=2; $i<=sqrt($value); $i++){
		if ($value % $i == 0 ){ $pr = 0; break; }
		 else { $pr = 1;}

    }
    if ($pr == 1) {$res[$k] = $value; $k=$k+1;}
}
$min = $res[0];
$max = $res[0];
foreach ($res as $value){
   if ($value > $max) $max = $value;
   if ($value < $min) $min = $value;
}
echo '<a>Array: </a>';
foreach ($array as $value) {
   print_r($value);
   echo ' ';
}
echo '<br>';
echo '<a>Array of simple numbers: </a>';
foreach ($res as $temp) {
   print_r($temp);
   echo ' ';
}
echo '<br>';
echo '<a>Max: </a>';
print_r($max);
echo '<br>';
echo '<a>Min: </a>';
print_r($min);

?>