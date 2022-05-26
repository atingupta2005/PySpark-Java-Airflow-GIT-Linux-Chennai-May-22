### User Information

1. **who** It is used to get information about currently logged in user on to system. If you don't provide any option or arguments, the command displays the following information for each logged-in user.

    1. Login name of the user
    2. User terminal
    3. Date & Time of login
    4. Remote host name of the user

   ```bash
   $ who
   sudheer :0 2019-08-04 01:21 (:0)
   ```

2. **whoami:** It display the system’s username

   ```bash
   $ whoami
   sudheer
   ```

3. **id:** It display the user identification(the real and effective user and group IDs) information

   ```bash
   $ id
   uid=1000(sj) gid=1000(sj) groups=1000(sj),4(adm),24(cdrom),27(sudo),30(dip),46(plugdev),120(lpadmin),131(lxd),132(sambashare)
   ```
4. **groups:** This command is used to display all the groups for which the user belongs to.

   ```bash
   $ group
   sj: sj, adm, cdrom, sudo, dip, plugdev, lpadmin, lxd, sambashare
   ```

5. **finger:**  Used to check the information of any currently logged in users. i.e, It displays users login time, tty (name), idle time, home directory, shell name etc.

   ```bash
   $ finger
   Login     Name       Tty      Idle  Login Time   Office     Office Phone
   sj        sj        *:0             Aug 28 01:27 (:0)
   ```

   This may not be available by default in many linux machines. In this case, you need to install it manually.

   ```bash
   $ sudo apt install finger
   ```
6. **users:** Displays usernames of all users currently logged on the system.

   ```bash
   $ users
   sj
   ```

7. **grep:** It  is a powerful pattern searching tool to find information about a specific user from the system accounts file: /etc/passwd.

    ```bash
    $ grep -i sj /etc/passwd
    sj:x:1000:1000:sj,,,:/home/sj:/bin/bash
    ```

### File and directory commands

1. **pwd** The pwd(Present Working Directory) command is used to print the name of the present/current working directory starting from the root.
   ```bash
   $ pwd
   /home/sj/Desktop/Linux
   ```

2. **ls**: The `ls` command is used to list files or directories. It also accepts some flags or options that changes how files or directories are listed in your terminal.

    ```bash
     Syntax:
     ls [flags] [directory]

     Example:
     $ ls
     bin dev lib libx32 mnt  

     //Listing files & directories with time in a reverse order
     $ ls -ltr
     drwxr-xr-x 2 sj sj 4096 May 14  2020 Videos
     drwxr-xr-x 2 sj sj 4096 May 14  2020 Templates
     drwxr-xr-x 2 sj sj 4096 May 14  2020 Public

     //Home directory
     $ ls ~
     Desktop    Downloads  Pictures  Sudheer    test   test.txt
     Documents  Music      Public    Templates  test1  Videos
    ```

    Below are the list of possible options for `ls` command,

3. **mkdir** The mkdir(make directory) command allows users to create directories or folders.

   ```bash
   $ mkdir ubuntu
   $ ls
   ubuntu
   ```

   The option '-p' is used to create multiple directories or parent directories at once.

   ```bash
   $ mkdir -p dir1/dir2/dir3
   $ cd dir1/dir2/dir3
   ~/Desktop/Linux/dir1/dir2/dir3$
   ```

4. **rmdir**: The rmdir(remove directories) is used to remove _empty_ directories. Can be used to delete multiple empty directories as well. Safer to use compared to `rm -r FolderName`. This command can also be forced to delete non-empty directories.

   1. Remove empty directory:

   ```bash
   rmdir FolderName
   ```

   2. Remove multiple directories:

   ```bash
   rmdir FolderName1 FolderName2 FolderName3
   ```

   3. Remove non-empty directories:

   ```bash
   rmdir FolderName1 --ignore-fail-on-non-empty
   ```

5. **rm**: The rm(remove) command is used to remove objects such as files, directories, symbolic links etc from the file system.
   1. Remove file: The rm command is used to remove or delete a file
   ```bash
   rm file_name
   ```
   2. Remove file forcefully: The rm command with -f option is used for removal of file without prompting for confirmation.
   ```bash
   rm -f filename
   ```
   3. Remove directory: The rm command with -r option is used to remove the directory and its contents recursively.
   ```bash
   rm -r myDir
   ```
   4. Remove directory forcefully: The rm command with -rf option is used to forcefully remove directory recursively.
   ```bash
   rm -rf myDir
   ```
6. **touch**: The touch command is used to create, change and modify timestamps of a file without any content.
   1. **Create a new file:** You can create a single file at a time using touch command. The file created is an empty file.
       ```bash
       touch file_name
       ```
   2. **Create multiple files:** You can create the multiple numbers of files at the same time.
       ```bash
       touch file1_name file2_name file3_name
       ```
  7. **cat**: The cat command is used to create single or multiple files, view contain of file, concatenate files and redirect output in terminal or files.
     ```bash
     $ cat [OPTION] [FILE]...
     ```
   1. **View file contents:** You can view contents of a single or more files by mentioning the filenames.

       ```bash
       cat file_name1 file_name2
       ```
   2. **More & Less options:** If a file having a large number of content that won’t fit in the output terminal then `more` & `less` options can be used to indiate additional content.

       ```bash
       cat file_name1.txt | more
       cat file_name1.txt | less
       ```

### File permissions
Since Linux is a multi-user operating system, it is necessary to provide security to prevent people from accessing each other’s confidential files.
So Linux divides authorization into 2 levels,

1. **Ownership:**
Each file or directory has assigned with 3 types of owners
i. **User:** Owner of the file who created it.
ii. **Group:** Group of users with the same access permissions to the file or directory.
iii. **Other:** Applies to all other users on the system

2. **Permissions:**
Each file or directory has following permissions for the above 3 types of owners.

    i.   **Read:** Give you the authority to open and read a file and lists its content for a directory.

    ii.  **Write:** Give you the authority to modify the contents of a file and add, remove and rename files stored in the directory.

    iii. **Execute:** Give you the authority to run the program in Unix/Linux.

     The permissions are indicated with below characters,

         r = read permission

         w = write permission

         x = execute permission

         \- = no permission

There is a need to restrict own file/directory access to others.

**Change access:**
The `chmod` command is used to change the access mode of a file.  This command is used to set permissions (read, write, execute) on a file/directory for the owner, group and the others group.

```cmd
chmod ugo-rwx test.txt
```

There are 2 ways to use this command,


**Changing Ownership and Group:**
It is possible to change the ownership and group of a file/directory using `chown` command.

```cmd
chown John test.txt
chown John:Admin test.txt
```

### Networking

1.  **Display network information:** `ifconfig` command is used to display all network information(ip address, ports etc)

```cmd
ifconfig -a
```

2.  **Test connection to a remote machine:** Send an echo request to test connection of a remote machine.

    ```cmd
    ping <ip-address> or hostname

    Example:
    ping 10.0.0.11
    ```

3.  **Show IP Address:** Display ip address of a currennt machine

    ```cmd
    ip addr show
    ```

### Installing packages

1. **Install package:**

```cmd
sudo apt install package_name
```

2. **Uninstall package:**
The remove command is used to remove or uninstall package name.
```cmd
sudo apt remove package_name
```

### Disk usage

1.  **Synopsis:** `du` command is used to check the information of disk usage of files and directories on a machine

```cmd
du [OPTION]... [FILE]...
```

2.  **Disk usage of a directory:** To find out the disk usage summary of a /home/ directory tree and each of its sub directories

```cmd
du  /home/
```

3.  **Disk usage in human readable format:** To find out the disk usage in human readable format

```cmd
du  -h /home/
```

4.  **Total disk usage of all files and directories upto certain depth:** print the total for a directory only if it is N or fewer levels below the command

```cmd
du  -ah --max-depth 2 /home/
```

### System and Hardware information

1.  **Print all information**: `uname` is mainly used to print system information.

```bash
$ uname -a
```

2.  **Print kernel name**:

```bash
$ uname -s
```

3.  **Print kernel release**:

```bash
$ uname -r
```

4.  **Print Architecture**:

```bash
$ uname -m
```

5.  **Print Operating System**:

```bash
$ uname -o
```

### Search Files

1. **Pattern search:**
The `grep` command is used to search patterns in files.

```cmd
grep pattern files
grep -i // Case sensitive

Example:
grep -i "hELLo" text.txt // Hello John
```

2. **Find files and directories:**

The `find` command is used to find or search files and directories by file name, folder name, creation date, modification date, owner and permissions etc and perform subsequent operations on them.

i. **Search file with name:**

```cmd
find ./directory_name -name file_name

Example:
find ./test -name test.txt // ./test/test.txt
```

3. **Whereis to locate binary or source files for a command:**
The whereis command in Linux is used to locate the binary, source, and manual page files for a command. i.e, It is used to It is used to find executables of a program, its man pages and configuration files.

```cmd
whereis command_name

Example:
whereis netstat //netstat:  /bin/netstat /usr/share/man/man8/netstat.8.gz(i.e, executable and location of its man page)
```

4. **Locate to find files:**
The locate command is used to find the files by name. This command is faster compared to find command because it searches database for the filename instead of searching your filesystem.

```cmd
locate [OPTION] PATTERN

Example:
locate "*.txt" -n 10 // 10 file search results ending with .txt extension
```

### SSH

SSH (Secure Shell) is a network protocol that enables secure remote connections between two systems.

1.  **Connect remote machine using IP address or machine name:** The remote server can be connected with local user name using either host name or IP address

```cmd
ssh <host-name> or <ip-address>

Example:
ssh 192.111.66.100
ssh test.remoteserver.com
```

2.  **Connect remote machine using username:** It is also possible specify a user for an SSH connection.

```cmd
ssh username@hostname_or_ip-address

Example:
ssh john@192.0.0.22
ssh john@test.remoteserver.com
```

### Vi/Vim-commands

Vi editor is the most popular text editor from the early days of Unix. Whereas Vim(Vi IMproved) is an improved version of vi editor to be used in CLI (command line interface) for mainly text editing tasks in many configuration files. Some of the other alternatives are Elvis, Nvi, Nano, Joe, and Vile.
It has two main operation modes,

1.  **Command Mode:** It allows the entry of commands to manipulate text.
2.  **Entry mode(Or Insert mode):** It allows typed characters on the keyboard into the current file.

#### 1. Start with Vi Editor

You can create a new file or open an existing file using `vi filename` command.

```cmd
 vi <filename_NEW> or <filename_EXISTING> // Create a new file or open an existing file

 Example:
 vi first.txt
```

Let's see how do you create file, enter the content and leave the CLI by saving the changes.

1.  Create a new file named `first.txt`
2.  Press `i` to enter the insert mode
3.  Enter the text "Hello World!"
4.  Save the text and exit by pressing `:wq!` command
5.  Check the entered text

#### 2. Cursor movement

    These commands will be used in Command mode.

##### Inserting Text

These commands places vi in entry mode from command mode. First, you need to be in command mode to use the below commands.

###### Insert

```cmd
i    # Insert text to the left of the cursor
ESC  # Exit insert mode
```

##### Exiting

    These commands are used to exit from the file.
    ```cmd
    :w	    # Write (save) the file, but don't exit
    :wq	    # Write (save) and quit
    :q!	    # Quit and throw away for any changes
    ```

## Standard Output, Standard Error and Standard Input

```bash
echo "foo" > bar.txt       # Overwrite file with content
echo "foo" >> bar.txt      # Append to file with content
```

## Moving Files

```bash
cp foo.txt bar.txt                                # Copy file
mv foo.txt bar.txt                                # Move file
```

## Reading Files

```bash
cat foo.txt            # Print all contents
head foo.txt           # Print top 10 lines of file
tail -f foo.txt           # Print bottom 10 lines of file
```

## Compressing Files

### zip

Compresses one or more files into *.zip files.

```bash
zip foo.zip /bar.txt                # Compress bar.txt into foo.zip
zip foo.zip /bar.txt /baz.txt       # Compress bar.txt and baz.txt into foo.zip
zip foo.zip /{bar,baz}.txt          # Compress bar.txt and baz.txt into foo.zip
zip -r|--recurse-paths foo.zip /bar # Compress directory bar into foo.zip
```

## Decompressing Files

### unzip

```bash
unzip foo.zip          # Unzip foo.zip into current directory
```

### tar -x

```bash
tar -x|--extract -z|--gzip -f|--file=foo.tar.gz # Un-compress foo.tar.gz into current directory
tar -x|--extract -f|--file=foo.tar              # Un-combine foo.tar into current directory
```

## Memory Usage

```bash
free                   # Show memory usage
free -h|--human        # Show human readable memory usage
```

## Shutdown and Reboot

```bash
shutdown                     # Shutdown in 1 minute
reboot                       # Reboot now
```

## Identifying Processes

```bash
htop                   # List all processes interactively
```

## Killing Processes

```bash
CTRL+C                 # Kill a process running in the foreground
kill -9 PID            # Force shut down of process by PID. Sends SIGKILL signal.
```

## Date & Time

```bash
date                   # Print the date and time
```

## Scheduled Tasks

```pre
   *      *         *         *           *
Minute, Hour, Day of month, Month, Day of the week
```

```bash
crontab -l                 # List cron tab
crontab -e                 # Edit cron tab in Vim

* * * * * foo              # Run foo every minute
0 * * * * foo              # Run foo every hour
15 6 * * * foo             # Run foo daily at 6:15 AM
```

## HTTP Requests

```bash
curl https://example.com                               # Return response body
wget https://example.com/file.txt .                            # Download a file to the current directory
```

## Bash Script

### Variables

```bash
#!/bin/bash

foo=123                # Initialize variable foo with 123
declare -i foo=123     # Initialize an integer foo with 123
echo $foo              # Print variable foo
echo ${foo}_'bar'      # Print variable foo followed by _bar

export foo             # Make foo available to child processes
unset foo              # Make foo unavailable to child processes
```

### Environment Variables

```bash
#!/bin/bash

env        # List all environment variables
echo $PATH # Print PATH environment variable
```

### Functions

```bash
#!/bin/bash

greet() {
  local world = "World"
  echo "$1 $world"
  return "$1 $world"
}
greet "Hello"
greeting=$(greet "Hello")
```

### Exit Codes

```bash
#!/bin/bash

exit 0   # Exit the script successfully
exit 1   # Exit the script unsuccessfully
echo $?  # Print the last exit code
```
