#! /usr/bin/env bash

# $1 = Location to search
# $2 = Size to delete

result=`find $1 -type f -size +$2k -print -delete`

if [[ $result ]]
	then
	echo 'Deleting...'
	echo "$result"
	else
	echo "No files of size $2 kilobytes or larger found."
fi