<?php
$inputData = "yes";

while ($inputData == "yes") {
	$correct = "no";
	$teamNumber = 0;
	$matchNumber = 0;
	$autoBeaconsPressed = 0;
	$capballMoved = 0;
	$autoCornerParticles = 0;
	$autoCenterParticles = 0;
	$autoParking = 0;
	$teleopCornerParticles = 0;
	$teleopCenterParticles = 0;
	$teleopBeaconsPressed = 0;
	$endGameCapball = 0;
	while ($correct == "no") {
		$outputString="";
		echo "Entering data for Team Scouting ...\r\n";
		echo "Team Number: ";
		$handle = fopen ("php://stdin","r");
		$teamNumber = trim(fgets($handle));
		fclose($handle);
		echo "\n";

		echo "Match Number: ";
		$handle = fopen ("php://stdin","r");
		$matchNumber = trim(fgets($handle));
		fclose($handle);
		echo "\n";

		echo "Scoring 1: Auto Corner Particles Scored (if none, just hit enter)";
		$handle = fopen ("php://stdin","r");
		$inputValue = trim(fgets($handle));
		if ( strlen($inputValue) > 0 ) $autoCornerParticles = $inputValue;
		fclose($handle);
		echo "\n";

		echo "Scoring 2: Auto Capball Moved (1=y [0,enter]=n)";
		$handle = fopen ("php://stdin","r");
		$inputValue = trim(fgets($handle));
		if ( strlen($inputValue) > 0 )  $capballMoved = $inputValue;
		fclose($handle);
		echo "\n";

		echo "Scoring 3: Auto Center Particles Scored (if none, just hit enter)";
		$handle = fopen ("php://stdin","r");
		$inputValue = trim(fgets($handle));
		if ( strlen($inputValue) > 0 ) $autoCenterParticles = $inputValue;
		fclose($handle);
		echo "\n";

		echo "Scoring 4: Auto Beacons Pressed (if none, just hit enter)";
		$handle = fopen ("php://stdin","r");
		$inputValue = trim(fgets($handle));
		if ( strlen($inputValue) > 0 )  $autoBeaconsPressed = $inputValue;
		fclose($handle);
		echo "\n";

		echo "Scoring 5: Auto Parking  (if none, just hit enter)\r\n";
		echo "           1 - partially on corner\r\n";
		echo "           2 - completely on corner\r\n";
		echo "           3 - partially on center\r\n";
		echo "           4 - completely on center\r\n";
		$handle = fopen ("php://stdin","r");
		$inputValue = trim(fgets($handle));
		if ( strlen($inputValue) > 0 )  $autoParking = $inputValue;
		fclose($handle);
		echo "\n";

		echo "Scoring 6: TeleOp Corner Particles (if none, just hit enter)";
		$handle = fopen ("php://stdin","r");
		$inputValue = trim(fgets($handle));
		if ( strlen($inputValue) > 0 ) $teleopCornerParticles = $inputValue;
		fclose($handle);
		echo "\n";

		echo "Scoring 7: TeleOp Center Particles (if none, just hit enter)";
		$handle = fopen ("php://stdin","r");
		$inputValue = trim(fgets($handle));
		if ( strlen($inputValue) > 0 )  $teleopCenterParticles = $inputValue;
		fclose($handle);
		echo "\n";

		echo "Scoring 8: TeleOp Correct Beacon Colors (if none, just hit enter)";
		$handle = fopen ("php://stdin","r");
		$inputValue = trim(fgets($handle));
		if ( strlen($inputValue) > 0 )  $teleopBeaconsPressed = $inputValue;
		fclose($handle);
		echo "\n";

		echo "Scoring 9: EndGame Capball Lift (if none, just hit enter)\r\n";
		echo "           1 - low lift\r\n";
		echo "           2 - high lift\r\n";
		echo "           3 - vortex capped\r\n";
		$handle = fopen ("php://stdin","r");
		$inputValue = trim(fgets($handle));
		if ( strlen($inputValue) > 0 ) $endGameCapball = $inputValue;
		fclose($handle);
		echo "\n";

		$outputString = "";

		echo "Captured Results:\n";
		echo "Team Number: $teamNumber\r\n";
		echo "Match Number: $matchNumber\r\n";
		echo "Auto Corner Particles Scored: $autoCornerParticles\r\n";
		$outputString .= ",$autoCornerParticles";
		if ($capballMoved > 0) echo "Auto Capball Moved: yes\r\n";
		else echo "Auto Capball Moved: No\r\n";
		$outputString .= ",$capballMoved";
		echo "Auto Center Particles Scored: $autoCenterParticles\r\n";
		$outputString .= ",$autoCenterParticles";
		echo "Auto Beacons Pressed: $autoBeaconsPressed\r\n";
		$outputString .= $autoBeaconsPressed;
		switch($autoParking) {
			case 0: { $outputString .= ",1,0,0,0,0"; echo "Auto Parking: No\r\n";break; }
			case 1: { $outputString .= ",0,1,0,0,0"; echo "Auto Parking: Partial Corner\r\n";break; }
			case 2: { $outputString .= ",0,0,1,0,0"; echo "Auto Parking: Complete Corner\r\n";break; }
			case 3: { $outputString .= ",0,0,0,1,0"; echo "Auto Parking: Partial Center\r\n";break; }
			case 4: { $outputString .= ",0,0,0,0,1"; echo "Auto Parking: Complete Center\r\n";break; }
		}
		echo "TeleOp Corner Particles Scored: $teleopCornerParticles\r\n";
		$outputString .= ",$teleopCornerParticles";
		echo "TeleOp Center Particles Scored: $teleopCenterParticles\r\n";
		$outputString .= ",$teleopCenterParticles";
		echo "TeleOp Correct Beacons Pressed: $teleopBeaconsPressed\r\n";
		$outputString .= ",$teleopBeaconsPressed";
			echo "TeleOp Correct Beacons Pressed: $teleopBeaconsPressed\r\n";
		switch($endGameCapball) {
			case 0: { $outputString .= ",1,0,0,0"; echo "Endgame Capball - No Lift\r\n";break; }
			case 1: { $outputString .= ",0,1,0,0"; echo "Endgame Capball - Low Lift\r\n";break; }
			case 2: { $outputString .= ",0,0,1,0"; echo "Endgame Capball - High Lift\r\n";break; }
			case 3: { $outputString .= ",0,0,0,1"; echo "Endgame Capball - Vortex Capped\r\n";break; }
		}
		echo "\nDoes this look correct?\n";
		$handle = fopen ("php://stdin","r");
		$line = fgets($handle);
		fclose($handle);
		if ((trim($line) == 'yes') || (trim($line) == 'y')) $correct = "yes";
	}
	# echo "output string for $teamNumber-$matchNumber is: $outputString\r\n";
	$outputFilename = "./data/$teamNumber-$matchNumber.txt";
	file_put_contents($outputFilename,$outputString);

	echo "\nEnter another match?\n";
	$handle = fopen ("php://stdin","r");
	$line = fgets($handle);
	fclose($handle);
	if ((trim($line) == 'no') || (trim($line) == 'n')) $inputData = "no";
}
?>