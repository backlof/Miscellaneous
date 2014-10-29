#! /usr/bin/env bash

# $1 = Location to search
# $2 = Modified within number of days

find $1 -type f -mtime -$2 -printf '%kK\t%p\n' | sort -n