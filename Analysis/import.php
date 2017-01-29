<?php

# initial static variables
$sourceDirectory="processed";
$htmlDirectory = "html";
# scoring elements
$scoringArray = array();
$scoringArray[1] = array("Corner Particles",5);
$scoringArray[2] = array("Capball Moved",5);
$scoringArray[3] = array("Center Particles",15);
$scoringArray[4] = array("Beacons Pressed",30);
$scoringArray[5] = array("No Parking",0);
$scoringArray[6] = array("Partial Corner Parked",5);
$scoringArray[7] = array("Complete Corner Parked",10);
$scoringArray[8] = array("Partial Center Parked",5);
$scoringArray[9] = array("Complete Center Parked",10);
$scoringArray[10] = array("Corner Particles",1);
$scoringArray[11] = array("Center Particles",5);
# half the actual value of this scoring element for analysis as assuming each team in an alliance will have hit half of the beacons.
# $scoringArray[12] = array("Correct Beacon Color&nbsp;*",10);
$scoringArray[12] = array("Correct Beacon Color&nbsp;*",5);
$scoringArray[13] = array("No Capball Lift",0);
$scoringArray[14] = array("Low Capball Lift",10);
$scoringArray[15] = array("High Capball Lift",20);
$scoringArray[16] = array("Capped Vortex",40);

# working variables
$teamNumbers = "";
$dataArray = array();
$teamNumberValue = 0;
$teamNames = array();
$mainPageSummary = array();
$summaryLoopCount = 0;
#$beginning = "<html><body bgcolor=#cc3300>";
$beginning = "<html><body>";

#create array of team names based on number:
foreach(file('./content/teams.txt') as $line) {
	$teamNumberPos = strpos($line,":");
	$teamNumber = trim(substr($line,0,$teamNumberPos));
	$teamName = trim(substr($line,$teamNumberPos + 1));
	if ($teamNumber != NULL) $teamNames[$teamNumber] = $teamName;
}

# get list of files in processed directory and process max score for each element for later use
$listOfFiles=array_slice(scandir($sourceDirectory), 2);
natsort($listOfFiles);
$filecount = 0;

#set up maximum score for each scoring element
$maxHitsCount[1] = 0;
$maxHitsCount[2] = 0;
$maxHitsCount[3] = 0;
$maxHitsCount[4] = 0;
$maxHitsCount[5] = 0;
$maxHitsCount[6] = 0;
$maxHitsCount[7] = 0;
$maxHitsCount[8] = 0;
$maxHitsCount[9] = 0;
$maxHitsCount[10] = 0;
$maxHitsCount[11] = 0;
$maxHitsCount[12] = 0;
$maxHitsCount[13] = 0;
$maxHitsCount[14] = 0;
$maxHitsCount[15] = 0;
$maxHitsCount[16] = 0;

$processingTeamNumber = 0;
$matchCounter = 0;
$aggregateHitsCount1 = 0; $aggregateHitsCount2 = 0; $aggregateHitsCount3 = 0; $aggregateHitsCount4 = 0;
$aggregateHitsCount5 = 0; $aggregateHitsCount6 = 0; $aggregateHitsCount7 = 0; $aggregateHitsCount8 = 0;
$aggregateHitsCount9 = 0; $aggregateHitsCount10 = 0; $aggregateHitsCount11 = 0; $aggregateHitsCount12 = 0;
$aggregateHitsCount13 = 0; $aggregateHitsCount14 = 0; $aggregateHitsCount15 = 0; $aggregateHitsCount16 = 0;

	foreach ($listOfFiles as $filename) { #loop through all datafiles
	#get team number
	$teamNumber_pos = strpos($filename,'-');
	$teamNumber = substr($filename,0,$teamNumber_pos);
	if (($teamNumber != $processingTeamNumber) && ($processingTeamNumber != 0)) {
		if (($aggregateHitsCount1 / $matchCounter) > $maxHitsCount[1]) $maxHitsCount[1] = $aggregateHitsCount1 / $matchCounter;
		if (($aggregateHitsCount2 / $matchCounter) > $maxHitsCount[2]) $maxHitsCount[2] = $aggregateHitsCount2 / $matchCounter;
		if (($aggregateHitsCount3 / $matchCounter) > $maxHitsCount[3]) $maxHitsCount[3] = $aggregateHitsCount3 / $matchCounter;
		if (($aggregateHitsCount4 / $matchCounter) > $maxHitsCount[4]) $maxHitsCount[4] = $aggregateHitsCount4 / $matchCounter;
		if (($aggregateHitsCount5 / $matchCounter) > $maxHitsCount[5]) $maxHitsCount[5] = $aggregateHitsCount5 / $matchCounter;
		if (($aggregateHitsCount6 / $matchCounter) > $maxHitsCount[6]) $maxHitsCount[6] = $aggregateHitsCount6 / $matchCounter;
		if (($aggregateHitsCount7 / $matchCounter) > $maxHitsCount[7]) $maxHitsCount[7] = $aggregateHitsCount7 / $matchCounter;
		if (($aggregateHitsCount8 / $matchCounter) > $maxHitsCount[8]) $maxHitsCount[8] = $aggregateHitsCount8 / $matchCounter;
		if (($aggregateHitsCount9 / $matchCounter) > $maxHitsCount[9]) $maxHitsCount[9] = $aggregateHitsCount9 / $matchCounter;
		if (($aggregateHitsCount10 / $matchCounter) > $maxHitsCount[10]) $maxHitsCount[10] = $aggregateHitsCount10 / $matchCounter;
		if (($aggregateHitsCount11 / $matchCounter) > $maxHitsCount[11]) $maxHitsCount[11] = $aggregateHitsCount11 / $matchCounter;
		if (($aggregateHitsCount12 / $matchCounter) > $maxHitsCount[12]) $maxHitsCount[12] = $aggregateHitsCount12 / $matchCounter;
		if (($aggregateHitsCount13 / $matchCounter) > $maxHitsCount[13]) $maxHitsCount[13] = $aggregateHitsCount13 / $matchCounter;
		if (($aggregateHitsCount14 / $matchCounter) > $maxHitsCount[14]) $maxHitsCount[14] = $aggregateHitsCount14 / $matchCounter;
		if (($aggregateHitsCount15 / $matchCounter) > $maxHitsCount[15]) $maxHitsCount[15] = $aggregateHitsCount15 / $matchCounter;
		if (($aggregateHitsCount16 / $matchCounter) > $maxHitsCount[16]) $maxHitsCount[16] = $aggregateHitsCount16 / $matchCounter;
		$aggregateHitsCount1 = 0; $aggregateHitsCount2 = 0; $aggregateHitsCount3 = 0; $aggregateHitsCount4 = 0;
		$aggregateHitsCount5 = 0; $aggregateHitsCount6 = 0; $aggregateHitsCount7 = 0; $aggregateHitsCount8 = 0;
		$aggregateHitsCount9 = 0; $aggregateHitsCount10 = 0; $aggregateHitsCount11 = 0; $aggregateHitsCount12 = 0;
		$aggregateHitsCount13 = 0; $aggregateHitsCount14 = 0; $aggregateHitsCount15 = 0; $aggregateHitsCount16 = 0;
		$matchCounter = 0;
	}
	$processingTeamNumber = $teamNumber;
	$matchCounter++;
	$matchData = file_get_contents($sourceDirectory . "/" . $filename);
	$matchResults = explode(",",$matchData);
	$aggregateHitsCount1 = $aggregateHitsCount1 + $matchResults[0];
	$aggregateHitsCount2 = $aggregateHitsCount2 + $matchResults[1];
	$aggregateHitsCount3 = $aggregateHitsCount3 + $matchResults[2];
	$aggregateHitsCount4 = $aggregateHitsCount4 + $matchResults[3];
	$aggregateHitsCount5 = $aggregateHitsCount5 + $matchResults[4];
	$aggregateHitsCount6 = $aggregateHitsCount6 + $matchResults[5];
	$aggregateHitsCount7 = $aggregateHitsCount7 + $matchResults[6];
	$aggregateHitsCount8 = $aggregateHitsCount8 + $matchResults[7];
	$aggregateHitsCount9 = $aggregateHitsCount9 + $matchResults[8];
	$aggregateHitsCount10 = $aggregateHitsCount10 + $matchResults[9];
	$aggregateHitsCount11 = $aggregateHitsCount11 + $matchResults[10];
	$aggregateHitsCount12 = $aggregateHitsCount12 + $matchResults[11];
	$aggregateHitsCount13 = $aggregateHitsCount13 + $matchResults[12];
	$aggregateHitsCount14 = $aggregateHitsCount14 + $matchResults[13];
	$aggregateHitsCount15 = $aggregateHitsCount15 + $matchResults[14];
	$aggregateHitsCount16 = $aggregateHitsCount16 + $matchResults[15];
}

if (($aggregateHitsCount1 / $matchCounter) > $maxHitsCount[1]) $maxHitsCount[1] = $aggregateHitsCount1 / $matchCounter;
if (($aggregateHitsCount2 / $matchCounter) > $maxHitsCount[2]) $maxHitsCount[2] = $aggregateHitsCount2 / $matchCounter;
if (($aggregateHitsCount3 / $matchCounter) > $maxHitsCount[3]) $maxHitsCount[3] = $aggregateHitsCount3 / $matchCounter;
if (($aggregateHitsCount4 / $matchCounter) > $maxHitsCount[4]) $maxHitsCount[4] = $aggregateHitsCount4 / $matchCounter;
if (($aggregateHitsCount5 / $matchCounter) > $maxHitsCount[5]) $maxHitsCount[5] = $aggregateHitsCount5 / $matchCounter;
if (($aggregateHitsCount6 / $matchCounter) > $maxHitsCount[6]) $maxHitsCount[6] = $aggregateHitsCount6 / $matchCounter;
if (($aggregateHitsCount7 / $matchCounter) > $maxHitsCount[7]) $maxHitsCount[7] = $aggregateHitsCount7 / $matchCounter;
if (($aggregateHitsCount8 / $matchCounter) > $maxHitsCount[8]) $maxHitsCount[8] = $aggregateHitsCount8 / $matchCounter;
if (($aggregateHitsCount9 / $matchCounter) > $maxHitsCount[9]) $maxHitsCount[9] = $aggregateHitsCount9 / $matchCounter;
if (($aggregateHitsCount10 / $matchCounter) > $maxHitsCount[10]) $maxHitsCount[10] = $aggregateHitsCount10 / $matchCounter;
if (($aggregateHitsCount11 / $matchCounter) > $maxHitsCount[11]) $maxHitsCount[11] = $aggregateHitsCount11 / $matchCounter;
if (($aggregateHitsCount12 / $matchCounter) > $maxHitsCount[12]) $maxHitsCount[12] = $aggregateHitsCount12 / $matchCounter;
if (($aggregateHitsCount13 / $matchCounter) > $maxHitsCount[13]) $maxHitsCount[13] = $aggregateHitsCount13 / $matchCounter;
if (($aggregateHitsCount14 / $matchCounter) > $maxHitsCount[14]) $maxHitsCount[14] = $aggregateHitsCount14 / $matchCounter;
if (($aggregateHitsCount15 / $matchCounter) > $maxHitsCount[15]) $maxHitsCount[15] = $aggregateHitsCount15 / $matchCounter;
if (($aggregateHitsCount16 / $matchCounter) > $maxHitsCount[16]) $maxHitsCount[16] = $aggregateHitsCount16 / $matchCounter;

foreach ($listOfFiles as $filename) {
	$filecount++;
	$teamNumber_pos = strpos($filename,'-');
	$teamNumber = substr($filename,0,$teamNumber_pos);
	if ($teamNumbers == "") $teamNumbers = $teamNumber;
	else {
		if ($teamNumber <> $teamNumberValue) {
			$teamNumbers .= "," . $teamNumber;
			$teamNumberValue = $teamNumber; # do this to prevent the same team getting listed more than once
		}
	}
	$matchNumber_pos=strpos($filename,".");
	$matchNumber = substr($filename,$teamNumber_pos+1,$matchNumber_pos - $teamNumber_pos - 1);
	$matchData = file_get_contents($sourceDirectory . "/" . $filename);
	$matchResults = explode(",",$matchData);
	$dataArray[] = array($teamNumber,$matchNumber,$matchResults);
	}

$teamNumbersArray = explode(",",$teamNumbers);
$teamNumbersCount = count($teamNumbersArray);
$teamNumbersCountPlusOne = $teamNumbersCount + 1;
$htmlHeader = "<table border=0><tr><td><img src='./GearInverted7.png' height=100px>&nbsp;&nbsp;&nbsp;&nbsp;</td><td><img src='./FTCicon.png' height=100px>&nbsp;&nbsp;</td><td>";
$htmlHeader .= "<table border=1><tr><td colspan=$teamNumbersCountPlusOne><center>Teams</td></tr>\r\n<tr>";


#start looping through each team's data
foreach ($teamNumbersArray as $teamNumberIndex=>$teamNumber) {
	#generate header information to list all teams, split into 10-column rows if needed
	if (($teamNumberIndex % 10) == 0) {
		$htmlHeader .= "</tr><tr>";
	}
	$htmlHeader .= "<td><center>&nbsp;<a href=$teamNumber.html>$teamNumber</a>&nbsp;</td>";
	$teamBody = "<table border=1><tr><td rowspan=3 valign=bottom bgcolor='#c5d9f1'>&nbsp;<center>Team&nbsp;</td><td rowspan=3 valign=bottom bgcolor='#c5d9f1'><center>&nbsp;Match&nbsp;</td><td rowspan=3 width=1px bgcolor=black></td><td colspan=20><center><b>Detailed Team Results</td></tr><tr>\r\n";
	$summaryBody = "<table border=1><tr><td rowspan=3 valign=bottom bgcolor='#c5d9f1'>&nbsp;<center>Team&nbsp;</td><td rowspan=3 valign=bottom bgcolor='#c5d9f1'><center>&nbsp;Match Count&nbsp;</td><td rowspan=3 valign=bottom bgcolor='#c5d9f1'><center>&nbsp;Rank&nbsp;</td><td rowspan=3 width=1px bgcolor=black></td><td colspan=20><center><b>Scoring Results (average)</td></tr><tr>\r\n";
	$teamBody .= "<td colspan=9 bgcolor='#fde9d9'><center>&nbsp;Auto&nbsp;</td><td rowspan=3 width=1px bgcolor=black></td><td colspan=3 bgcolor='#f2dcdb'><center>&nbsp;TeleOp&nbsp;</td><td rowspan=3 width=1px bgcolor=black></td><td colspan=4 bgcolor='#ebf1de'><center>&nbsp;EndGame&nbsp;</td><td rowspan=3 width=1px bgcolor=black></td><td rowspan=2><center>&nbsp;Estimated&nbsp;<br>&nbsp;Scoring&nbsp;<br>&nbsp;Contribution&nbsp;</td></tr><tr>";
	$summaryBody .= "<td colspan=9 bgcolor='#fde9d9'><center>&nbsp;Auto&nbsp;</td><td rowspan=3 width=1px bgcolor=black></td><td colspan=3 bgcolor='#f2dcdb'><center>&nbsp;TeleOp&nbsp;</td><td rowspan=3 width=1px bgcolor=black></td><td colspan=4 bgcolor='#ebf1de'><center>&nbsp;EndGame&nbsp;</td><td rowspan=3 width=1px bgcolor=black></td><td rowspan=2><center>&nbsp;Estimated&nbsp;<br>&nbsp;Average&nbsp;<br>&nbsp;Scoring&nbsp;<br>&nbsp;Contribution&nbsp;</td></tr><tr>";

	# list title of each scoring element
	foreach ($scoringArray as $scoringElementKey => $scoringElement) {
		if ($scoringElementKey < 10) $bgcolor="#fde9d9";
		elseif ($scoringElementKey < 13) $bgcolor="#f2dcdb";
		else $bgcolor="#ebf1de";
		$teamBody .= "<td bgcolor=$bgcolor><center>$scoringElement[0]</center></td>\r\n";
		$summaryBody .= "<td bgcolor=$bgcolor><center>$scoringElement[0]</center></td>\r\n";
	}
	$teamBody .= "</tr>\r\n";
	$teamBody .= "<tr><td colspan=23 bgcolor='black' height=1px></td></tr>";
	$summaryBody .= "</tr>\r\n";
	$summaryBody .= "<tr><td colspan=24 bgcolor='black' height=1px></td></tr>";

	#set up variables
	$matchCount=0;
	$totalPoints=0;

	#index will be data index
	#field 0 will be hits
	#field 1 will be score
	$scoringSummary[1][0] = 0; $scoringSummary[1][1] = 0;
	$scoringSummary[2][0] = 0; $scoringSummary[2][1] = 0;
	$scoringSummary[3][0] = 0; $scoringSummary[3][1] = 0;
	$scoringSummary[4][0] = 0; $scoringSummary[4][1] = 0;
	$scoringSummary[5][0] = 0; $scoringSummary[5][1] = 0;
	$scoringSummary[6][0] = 0; $scoringSummary[6][1] = 0;
	$scoringSummary[7][0] = 0; $scoringSummary[7][1] = 0;
	$scoringSummary[8][0] = 0; $scoringSummary[8][1] = 0;
	$scoringSummary[9][0] = 0; $scoringSummary[9][1] = 0;
	$scoringSummary[10][0] = 0; $scoringSummary[10][1] = 0;
	$scoringSummary[11][0] = 0; $scoringSummary[11][1] = 0;
	$scoringSummary[12][0] = 0; $scoringSummary[12][1] = 0;
	$scoringSummary[13][0] = 0; $scoringSummary[13][1] = 0;
	$scoringSummary[14][0] = 0; $scoringSummary[14][1] = 0;
	$scoringSummary[15][0] = 0; $scoringSummary[15][1] = 0;
	$scoringSummary[16][0] = 0; $scoringSummary[16][1] = 0;

	if (isset($stdDevArray)) unset($stdDevArray); #if array exists, kill it so it can be created fresh
	$stdDevArray = array();
	#read through data for each match
	foreach ($dataArray as $matchResults) {
		$matchPointTotal = 0;
		if ($matchResults[0] == $teamNumber) { #results are for this team
			#set up left columns of table
			$teamBody .= "<tr><td align=right bgcolor='#c5d9f1'>&nbsp;$matchResults[0]&nbsp;</td><td align=right bgcolor='#c5d9f1'>&nbsp;$matchResults[1]&nbsp;</td>\r\n";
			$teamBody .= "<td width=1px bgcolor=black></td>";
			#report "hits" for each scoring element
			foreach ($matchResults[2] as $scoringIndex=>$scoringElement) {
				$scoringElement = trim($scoringElement);
				$matchPointTotal = $matchPointTotal + ($scoringArray[$scoringIndex+1][1] * $scoringElement);
				$scoringSummary[$scoringIndex+1][0] = $scoringSummary[$scoringIndex+1][0] + $scoringElement;
				$scoringSummary[$scoringIndex+1][1] = $scoringSummary[$scoringIndex+1][1] + ($scoringArray[$scoringIndex+1][1] * $scoringElement);
				if ($scoringIndex < 9) $teamBody .= "<td align=right bgcolor='#fde9d9'>&nbsp;$scoringElement&nbsp;</td>";
				elseif ($scoringIndex == 9) $teamBody .= "<td width=1px bgcolor=black></td><td align=right bgcolor='#f2dcdb'>&nbsp;$scoringElement&nbsp;</td>";
				elseif ($scoringIndex < 12) $teamBody .= "<td align=right bgcolor='#f2dcdb'>&nbsp;$scoringElement&nbsp;</td>";
				elseif ($scoringIndex == 12) $teamBody .= "<td width=1px bgcolor=black></td><td align=right bgcolor='#ebf1de'>&nbsp;$scoringElement&nbsp;</td>";
				else $teamBody .= "<td align=right bgcolor='#ebf1de'>&nbsp;$scoringElement&nbsp;</td>";
			}
			$totalPoints = $totalPoints + $matchPointTotal;
			$stdDevArray[]=$matchPointTotal;
			$matchCount++;
			$teamBody .= "<td width=1px bgcolor=black></td>";
			$teamBody .= "<td align=right>&nbsp;$matchPointTotal&nbsp;</td>";
			$teamBody .= "</tr>\r\n";
		}

	$teamInfo = "<center><font size=+2><b>$teamNumber";
	$teamName = $teamNames[$teamNumber];
	if ($teamName != "") $teamInfo .= "&nbsp;-&nbsp;$teamName";
	$teamInfo .= "</b></font><br>";
	}
	#calculate average number of elements scored line
	$teamBody .= "<tr><td colspan=23 bgcolor='black' height=1px></tr><tr><td colspan=2 align=right>&nbsp;Average Elements&nbsp;</td>";
	$teamBody .= "<td width=1px bgcolor=black></td>";
	$averageElementsScoredArray = array();
	foreach ($scoringSummary as $scoringKey => $scoringElements) {
		$averageElements = round($scoringElements[0] / $matchCount,2);
		if ($scoringKey < 9) $teamBody .= "<td align=right bgcolor='#fde9d9'>&nbsp;$averageElements&nbsp;</td>";
		elseif ($scoringKey == 9) $teamBody .= "<td align=right bgcolor='#fde9d9'>&nbsp;$averageElements&nbsp;</td><td width=1px bgcolor=black></td>";
		elseif ($scoringKey < 13) $teamBody .= "<td align=right bgcolor='#f2dcdb'>&nbsp;$averageElements&nbsp;</td>";
		elseif ($scoringKey == 13) $teamBody .= "<td width=1px bgcolor=black></td><td align=right bgcolor='#ebf1de'>&nbsp;$averageElements&nbsp;</td>";
		else $teamBody .= "<td align=right bgcolor='#ebf1de'>&nbsp;$averageElements&nbsp;</td>";
		$averageElementsScoredArray[$teamNumber][$scoringKey] = $averageElements;
	}
	$teamBody .= "<td width=1px bgcolor=black></td></tr>\r\n";

	#calculate percentage of total line
	$teamBody .= "<tr><td colspan=2 align=right>&nbsp;% of Total Team Score&nbsp;</td>";
	$teamBody .= "<td width=1px bgcolor=black></td>";
	foreach ($scoringSummary as $scoringKey => $scoringElements) {
		$totalPointsTemp = $totalPoints;
		if ($totalPointsTemp == 0) $totalPointsTemp = 1;
		$averagePercentage = round((($scoringElements[1] / $matchCount)/($totalPointsTemp / $matchCount))*100,1);
		$averagePercentage .= "%";
		if ($scoringKey < 9) $teamBody .= "<td align=right bgcolor='#fde9d9'>&nbsp;$averagePercentage&nbsp;</td>";
		elseif ($scoringKey == 9) $teamBody .= "<td align=right bgcolor='#fde9d9'>&nbsp;$averagePercentage&nbsp;</td><td width=1px bgcolor=black></td>";
		elseif ($scoringKey < 13) $teamBody .= "<td align=right bgcolor='#f2dcdb'>&nbsp;$averagePercentage&nbsp;</td>";
		elseif ($scoringKey == 13) $teamBody .= "<td width=1px bgcolor=black></td><td align=right bgcolor='#ebf1de'>&nbsp;$averagePercentage&nbsp;</td>";
		else $teamBody .= "<td align=right bgcolor='#ebf1de'>&nbsp;$averagePercentage&nbsp;</td>";
	}
	$averageTotalScore = round(($totalPoints / $matchCount),2);
	$teamBody .= "<td width=1px bgcolor=black></td></tr>\r\n";

	#calculate average score line
	$teamBody .= "<tr><td colspan=2 align=right>&nbsp;Average Score&nbsp;</td>";
	$teamBody .= "<td width=1px bgcolor=black></td>";

	#calculate top 3 scoring areas for a team
	$averageScoreCalculation = array();
	foreach ($scoringSummary as $scoringKey => $scoringElements) {
		#half the beacons scoring as assumed each alliance partner got half
		if ($scoringKey == 12) $averageScoreCalculation[] = round(($scoringElements[1] / $matchCount),2);
		else $averageScoreCalculation[] = round($scoringElements[1] / $matchCount,2);
	}
	rsort($averageScoreCalculation);
	$averageSummary = "";
	$averageMainPageSummary = "";
	foreach ($scoringSummary as $scoringKey => $scoringElements) {
		$highScoreIndicator="";

		if ($scoringKey < 10) $bgcolor="#fde9d9";
		elseif ($scoringKey < 13) $bgcolor="#f2dcdb";
		else $bgcolor="#ebf1de";

		if ($scoringKey == 12) $averageScore = round(($scoringElements[1] / $matchCount),2);
		else  $averageScore = round($scoringElements[1] / $matchCount,2);
		$tdimpact = "<td align=right bgcolor=$bgcolor>";
		$tdimpactSummary = "<td align=right bgcolor=$bgcolor>";
		$tdimpactFontFlag = "";
		if ($averageScore == 0);
		#yellows: ffcc00, eabb00, cba200
		#greens: 3aff00, 2cc200, 1f8800
		elseif ($averageScore == $averageScoreCalculation[0]) {
			$tdimpact = "<td align=right style='font-size:30px' bgcolor=#3aff00><b>";
			$tdimpactSummary = "<td align=right bgcolor=#3aff00>";
			$tdimpactFontFlag = "<font size=+2><b>";
		}
		elseif ($averageScore == $averageScoreCalculation[1]) {
			$tdimpact = "<td align=right style='font-size:25px' bgcolor=#2cc200><b>";
			$tdimpactSummary = "<td align=right bgcolor=#2cc200>";
			$tdimpactFontFlag = "<font size=+1><b>";
		}
		elseif ($averageScore == $averageScoreCalculation[2]) {
			$tdimpact = "<td align=right style='font-size:20px' bgcolor=#1f8800><b>";
			$tdimpactSummary = "<td align=right bgcolor=#1f8800>";
			$tdimpactFontFlag = "<b>";
		}

		$averageElementsScored = $averageElementsScoredArray[$teamNumber][$scoringKey];
		if ($maxHitsCount[$scoringKey] == $averageElementsScored) {
			if (($averageElementsScored != 0) && ($scoringKey != 5) && ($scoringKey != 13)){
				$highScoreIndicator="<font color=red>";
			}
		}

		$averageSummary .= "$tdimpact&nbsp;$highScoreIndicator$averageScore&nbsp;</td>";
		if ($scoringKey == 10) $averageMainPageSummary .= "<td width=1px bgcolor=black></td>";
		if ($scoringKey == 13) $averageMainPageSummary .= "<td width=1px bgcolor=black></td>";
		if ($scoringKey == 9) $averageSummary .= "<td width=1px bgcolor=black></td>";
		if ($scoringKey == 12) $averageSummary .= "<td width=1px bgcolor=black></td>";
		$averageMainPageSummary .= "$tdimpactSummary&nbsp;<font size=-2>($averageElementsScored)</font>&nbsp;&nbsp;&nbsp;$tdimpactFontFlag$highScoreIndicator$averageScore&nbsp;</td>";
	}

	#calculate standard deviation of each team total scores
	$matchesCount = count($stdDevArray);
	if ($matchesCount == 0) $matchesCount = 1;  # make sure to not divide by zero
	$matchesSum = array_sum($stdDevArray);
	$matchesAverage = $matchesSum/$matchesCount;
	$matchesDevSum = 0;
	foreach ($stdDevArray as $stdDevArrayValue) {
		$matchesDevSum += pow(($stdDevArrayValue - $matchesAverage),2);
		}
	$matchesDevAverage = $matchesDevSum / $matchesCount;
	$stdDevValue = round(sqrt($matchesDevAverage),1);
#echo "Standard Deviation: $stdDevValue\r\n";

	$averageMainPageSummary .= "<td width=1px bgcolor=black></td>";
	$teamBody .= $averageSummary;
	$averageTotalScore = round(($totalPoints / $matchCount),2);
	$mainPageSummary[$summaryLoopCount][0]=$averageTotalScore;
	$mainPageSummary[$summaryLoopCount][1]=$stdDevValue;
	$mainPageSummary[$summaryLoopCount][2]=$teamNumber;
	$mainPageSummary[$summaryLoopCount][3]=$teamName;
	$mainPageSummary[$summaryLoopCount][4]=$averageMainPageSummary;
	$mainPageSummary[$summaryLoopCount][5]=$matchCount;
	$summaryLoopCount++;



	$teamBody .= "<td width=1px bgcolor=black></td><td align=right>&nbsp;<b>$averageTotalScore</b>&nbsp;<br><font size=-2>&nbsp;StdDev:&nbsp;$stdDevValue&nbsp;</td>";
	$teamBody .= "</tr></table>\r\n";
	file_put_contents("./$htmlDirectory/$teamNumber.html","$teamInfo $teamBody");
}
arsort($mainPageSummary);
#array_multisort($mainPageSummary,
#print_r($mainPageSummary);

$htmlHeader .= "<td><center>&nbsp;<a href=index.html>Summary</a>&nbsp;</td></tr></table>";
$htmlHeader .= "<td>&nbsp;&nbsp;<img src='./FIRST-FTC17-VelocityVortex-Q-RGB.png' height=100px></td><td>&nbsp;&nbsp;&nbsp;&nbsp;<img src='./GearInverted7.png' height=100px></td></tr></table>";
$htmlBody = "";

$rankLoopCounter = 0;
$rankValue = 0;
$rankScorePrevious = 0;
$rankValuePrevious = 0;
foreach ($mainPageSummary as $mainPageTeamSummary) {
	$rankLoopCounter++;
	if ($rankScorePrevious != $mainPageTeamSummary[0]) {
		$rankValue = $rankLoopCounter;
		$rankValuePrevious = $rankValue;
		}
	else $rankValue = $rankValuePrevious;
	$rankValue = $rankValuePrevious;
	$rankScorePrevious = $mainPageTeamSummary[0];
	$summaryBody .= "<tr><td bgcolor='#c5d9f1'>
	                 <table border=0 bgcolor='#c5d9f1' width=100%><tr><td align=right bgcolor='#c5d9f1'><a href=$mainPageTeamSummary[2].html>$mainPageTeamSummary[2]</a></td></tr><tr><td align=left bgcolor='#c5d9f1'><font size=-2>$mainPageTeamSummary[3]</td></tr></table></td><td align=right bgcolor='#c5d9f1'>&nbsp;$mainPageTeamSummary[5]&nbsp;</td><td align=right bgcolor='#c5d9f1'>&nbsp;$rankValue&nbsp;</td><td width=1px bgcolor=black></td>$mainPageTeamSummary[4]</td><td align=right>&nbsp;<b>$mainPageTeamSummary[0]</b>&nbsp;<br><font size=-2>&nbsp;StdDev:&nbsp;$mainPageTeamSummary[1]&nbsp;</td></tr>";
}
$summaryBody .= "</table>";

$footer = "<br></center></center><ul>";
$footer .= "<li>*Correct Beacon Color values are halved, as it is assumed that on an alliance, each team will score half the beacons";
$footer .= "<li>Red numbers indicate highest average score during this competition";
$footer .= "</ul>";
$footer .= "<center><font size=-2>All content on these pages copyright © " . date('Y') . " Delta Robotics Team #9925";

$end = "</body></html>";

#rewrite each final file with header which is links for all teams
foreach ($teamNumbersArray as $teamNumber) {
	$tempFileContents = file_get_contents("./$htmlDirectory/$teamNumber.html");
	file_put_contents("./$htmlDirectory/$teamNumber.html", "<center>" . $htmlHeader . "<br>" . $tempFileContents . $footer . $end);
}

$footer = "<p></center><ul>";
$footer .= "<li>Each field has in parenthesis the average number of times scored, and the larger number is point total";
$footer .= "<li>*Correct Beacon Color values are halved, as it is assumed that on an alliance, each team will score half the beacons";
$footer .= "<li>Red numbers indicate highest average score during this competition";
$footer .= "</ul>";
$footer .= "<center><font size=-2>All content on these pages copyright © " . date('Y') . " Delta Robotics Team #9925";


file_put_contents("./$htmlDirectory/index.html", $beginning . "<center>$htmlHeader<br>$summaryBody" . $footer . $end);
echo "Processed $filecount records\r\n";
?>