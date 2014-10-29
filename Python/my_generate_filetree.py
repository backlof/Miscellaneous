#! /usr/bin/env python3

import sys      # System functions
import os       # Crossplatform
import random   # Random number generators

# Characters used in generation of random strings
legal_chars = 'abcdefghijklmnopqrstuvwxyz'+'ABCDEFGHIJKLMNOPQRSTUVWXYZ'+'0123456789_'

def random_string(length=6, prefix='', legal_chars=legal_chars):
    """
    Generates random strings with fixed length from characters in string

    :param length: fixed length of string (not including prefix)
    :param prefix: prefix for output
    :param legal_chars: string to choose from
    :return: void function
    """
    output = prefix

    # Slow algorithm for generating random string - noticeable on large lengths
    for x in range(0,length):
        output += legal_chars[random.randint(0,len(legal_chars)-1)]

    return output

def generate_tree(target, dirs=3, rec_depth=2, verbose=False):
    """
    Generate directories for the file tree in target

    :param target:
    :param dirs: maximum directories in each directory
    :param rec_depth: maximum depth for generation (recursive limit)
    :param verbose: bool print work
    :return: void function
    """

    if rec_depth > 0:

        limit = random.randint(0,dirs)

        for x in range(0,limit):

            name = os.path.join(target,random_string())

            if not os.path.exists(name):

                if verbose: print('Creating directory: ' + name)
                os.makedirs(name)
                generate_tree(name,dirs,rec_depth-1,verbose)  # Recursive call

def populate_tree(target, files=5, size=100, start_time=1388534400, end_time=1406851201000, verbose=False):
    """
    Populate file tree with files

    :param target:
    :param files: maximum files in each directory
    :param size: maximum size of each file
    :param start_time: minimum time for time stamp
    :param end_time: maximum time for time stamp
    :param verbose: bool print work
    :return: void function
    """
    for dirname in os.walk(target):

        file = dirname[2]

        if not file:  # If the item is not a file

            limit = random.randint(0,files)

            for x in range(0,limit):
                path = dirname[0]
                name = os.path.join(os.path.join(path, random_string()))
                if verbose: print('Creating file: ' + name)
                atime = random.randint(start_time,end_time)
                mtime = random.randint(start_time,end_time)

                # Random size in full kB
                text = random_string(random.randint(1,size)*1024)

                # Create or overwrite file
                with open(name, 'a+') as f:
                    f.write(text)

                os.utime(name,(atime,mtime))  # Change atime and mtime of file

# Enter if module is run as standalone program
if __name__ == "__main__":
    nargs = len(sys.argv)  # Number of arguments

    # Required arguments not set
    if nargs < 4:
        print('Not enough arguments included:')
        print('-target -dirs -files [-SIZE -REC_DEPTH -START -END -SEED -VERBOSE]')
        print('str     int   int    int    int         int   int  int   str')
        sys.exit(0)  # Stop script

    target = sys.argv[1]
    dirs = int(sys.argv[2])
    files = int(sys.argv[3])

    size = int(sys.argv[4]) if nargs > 4 else 100
    rec_depth = int(sys.argv[5]) if nargs > 5 else 2
    start = int(sys.argv[6]) if nargs > 6 else 1388534400
    end = int(sys.argv[7]) if nargs > 7 else 1406851200
    verbose = True if nargs > 9 else False

    if nargs > 8:
        random.seed(sys.argv[8] or None)

    generate_tree(target,dirs,rec_depth,verbose)
    populate_tree(target, files, size, start, end, verbose)