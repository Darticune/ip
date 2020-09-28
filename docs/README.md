# User Guide

## Features 

### Adding Todos to your recorded task list

Adds a Todo to the task list in Duke

#### Usage

#### `todo` - todo DESCRIPTION

- Example of usage:

    - `todo First Todo`

- Expected outcome:
    <br/>
    
    ```
    ____________________________________________________________
    Got it. I've added this task:
    [T][✘] First Todo
    Now you have 1 task in the list.
    ____________________________________________________________
    ```
<br/>
<br/>

### Adding Deadlines to your recorded task list

Adds a Deadline to the task list in Duke

#### Usage

#### `deadline` - deadline DESCRIPTION /by TIMELINE

- Example of usage:

    - `deadline First Deadline /by 15/06/1998`

- Expected outcome:
    <br/>
    
    ```
    ____________________________________________________________
    Got it. I've added this task:
    [D][✘] First Deadline (at: 15 Jun 1998)
    Now you have 1 task in the list.
    ____________________________________________________________
    ```
<br/>
<br/>

### Adding Events to your recorded task list

Adds a Event to the task list in Duke

#### Usage

#### `event` - event DESCRIPTION /at TIMELINE

- Example of usage:

    - `event First Event /at 15/06/1998`

- Expected outcome:
    <br/>
    
    ```
    ____________________________________________________________
    Got it. I've added this task:
    [E][✘] First Event (at: 15 Jun 1998)
    Now you have 1 task in the list.
    ____________________________________________________________
    ```
<br/>
<br/>

### Displaying all recorded tasks

Shows the lists of tasks that are recorded in Duke

#### Usage

#### `list`

- Example of usage:

    - `list`

- Expected outcome:
    <br/>

    ```
    ____________________________________________________________
    1. [E][✘] First Event (at: 15 Jun 1998)
    2. [D][✘] First Deadline (by: 15 Jun 2000)
    3. [T][✘] First Todo 
    ____________________________________________________________

    ```

<br/>
<br/>

### Completing a task in your recorded task list

Sets a task in the recorded list in Duke to the "complete" state from the "incomplete" state

#### Usage

#### `done` - done INDEX_OF_TASK_TO_COMPLETE

- Example of usage:

    - `done 1`

- Expected outcome:
    <br/>
    
    ```
    ____________________________________________________________
    Nice! I've marked this task as done:
    [E][✓] First Event (at: 15 Jun 1998).
    ____________________________________________________________
    ```
<br/>
<br/>

### Deleting a task in your recorded task list

Deletes a task from the task list in Duke

#### Usage

#### `delete` - delete INDEX_OF_TASK_TO_DELETE

- Example of usage:

    - `delete 1`

- Expected outcome:
    <br/>
    
    ```
    ____________________________________________________________
    Noted. I've removed this task:
    [E][✓] First Event (at: 15 Jun 1998)
    Now you have 2 tasks in the list.
    ____________________________________________________________
    ```
<br/>
<br/>

### Finding relevant tasks in your recorded task list

Searches through the task list in Duke for tasks containing a specified keyword

#### Usage

#### `find` - find KEYWORD

- Example of usage:

    - `find First`

- Expected outcome:
    <br/>
    
    ```
    ____________________________________________________________
    Here are the matching tasks in your list:
    1. [D][✘] First Deadline (by: 15 Jun 2000)
    2. [T][✘] First Todo
    ____________________________________________________________
    ```
<br/>
<br/>