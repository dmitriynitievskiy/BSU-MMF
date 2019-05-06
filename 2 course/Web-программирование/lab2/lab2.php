//в вещественном массиве наименьший элемент поместите на первое место, наименьший из оставшихся-
//на последнее место,следующий по величине-на второе место и так далее.

<?php
$array = array(12.3,13.5,42.8,46.12,68.43,23.1,12.4,3.5);
$res = array();

echo '<a>Array: </a>';
foreach ($array as $value) {
	print_r($value);
	echo ' ';
}

for ($i = 0; $i < count($array); $i++) {
	for ($j = $i + 1; $j < count($array); $j++) {
		if ($array[$i] > $array[$j]) {
			$temp = $array[$j];
			$array[$j] = $array[$i];
			$array[$i] = $temp;
		}
	}
}

echo '<br>';
echo '<a>Result: </a>';
$k = 0;
for ($i = 0; $i < count($array)/2; $i++) {
	$res[$i] = $array[$k];
	$k = $k + 2;
}

$k = count($array) - 1;
for ($i = count($array)/2 ; $i < count($array); $i++) {
	$res[$i] = $array[$k];
	$k = $k - 2;
}
foreach ($res as $value) {
	print_r($value);
	echo ' ';
}
?>