#!/bin/bash

# ----------------------------#
# CORE------------------------#
# ----------------------------#
function process {
	mkdir $project;
	cp -r android $project/$project-android;
	cp -r desktop $project/$project-desktop;
	cp -r core $project/$project-core;
	cd $project;
	find . -type f -name "*.java" -exec sed -i'' -e 's/com.rombus.evilbones.template/'$package'/g' {} +
}


# ----------------------------#
# CLI ------------------------#
# ----------------------------#
if [ $# -eq 2 ]; then
	project=$1
	package=$2
	process
	exit 0
elif [ $# -ne 0 ]; then
	echo ""
	echo "Creates a project folder using Flixel-GDX template."
	echo "  ./setup.sh <project name> <package name>"
	echo "  Run without arguments to launch the GUI."
	echo ""
	exit 1
fi


# ----------------------------#
# GUI ------------------------#
# ----------------------------#

title="Flixel-GDX template"
t=0.2

# ------------
# Gather data
# ------------

function zenityInput {
	local result=$(zenity --entry --title "$title" --text "$1" --entry-text "$2")
	echo $result
}
function checkCancel {
	if [ -z "$1" ]; then
		zenity --error --title="$title" --text "$2"
		exit $?
	fi
}

project=$(zenityInput "Insert project name:")
checkCancel "$project" "Cannot continue without a project name."

package=$(zenityInput "Insert package name:" "com.rombus.evilbones.")
checkCancel "$package" "Cannot continue without a package name."


# ------
# Setup 
# ------

process
(echo "10"; sleep $t;
 echo "20"; sleep $t;
 echo "35"; sleep $t;
 echo "45"; sleep $t; 
 echo "60"; sleep $t; 
 echo "75"; sleep $t; 
 echo "90"; sleep $t) | zenity --progress --title="$title" --ok-label="Done!" --text="Configuring template" --percentage=0

