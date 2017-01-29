@echo off
rem set HTTPS_PROXY=https://CCANET\saevans1:12sdfXCV!@secproxy.rockwellcollins.com:9090/
rem pip install openpyxl
if not exist html\. mkdir html
if not exist processed\. mkdir processed
if exist html\*.html del html\*.html
python import.py
c:\php\php import.php
