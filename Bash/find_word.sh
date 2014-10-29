#! /usr/bin/env bash

# $1 = Location to search
# $2 = Search term

# find $1 -type f | xargs grep $2 -n -H
result=`grep -r -n -H $2 $1`

if [[ $result ]]
	then
	echo "$result"
	else
	echo "No files containing $2 found."
fi