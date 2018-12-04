<?php
$servername = "127.0.0.1";
$username = "findmysong_admin";
$password = "LoveCS411!";
$my_db = "findmysong_database";
// $user_name = $_POST["username"];
$user_name="Yiqing";


$link = mysqli_connect($servername, $username, $password,$my_db);


if (!$link) {
    echo "Error: Unable to connect to MySQL";
    exit;
}


$sql="select track.track_name as name
from track,listen
where listen.user_id=
(SELECT user2.user_id
FROM listen as listen1, listen as listen2, 
user as user1,user as user2 
WHERE user1.display_name='$user_name' and user2.display_name!='$user_name'
and user1.user_id=listen1.user_id and user2.user_id=listen2.user_id
and listen1.track_id=listen2.track_id
group by user2.user_id,listen2.track_id
order by count(listen2.track_id)
limit 0,1)
and listen.track_id=track.track_id and track.track_name NOT IN (
    select track.track_name as name
  from track,listen,user
  WHERE user.display_name='$user_name' and user.user_id=listen.user_id
  and track.track_id=listen.track_id)";

$result = $link->query($sql);
$value=array();
if ($result->num_rows > 0) {
    while($row = $result->fetch_assoc()) {
        $value[]=$row;
    }
    echo(json_encode($value));
} else {
    echo "404";
}
mysqli_close($link);

?>

