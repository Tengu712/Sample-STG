@echo off

dir /s /B *.Java > sources.txt
javac --module-path "%PATH_TO_FX%" --add-modules javafx.controls -d bin -sourcepath src @sources.txt
del sources.txt
