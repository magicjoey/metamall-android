<?php
// array for JSON response
$response = array();
include("conn.php");
// check for required fields
if (isset($_POST['name']) && isset($_POST['password']) ) {
    
    $name = $_POST['name'];
    $password = $_POST['password'];
    $result = mysql_query("INSERT INTO test(name,password) VALUES('$name', '$password')");
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "Product successfully created.";

        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";
        
        // echoing JSON response
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}
?>