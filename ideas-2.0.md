
Motivation:

Typically a lab consists of unit tests and for each step the user is supposed to implement functionality that fulfills the tests. When the tests are passing, the user moves to the next step that adds more unit tests.

Although it would be possible to use a version control system to achieve similar results it is very useful to have all the steps available at all times when creating the lab. The reason is that the steps might be rearranged or text moved between the steps. However, when running the lab a version control system could be used to implement the necessary functionality.

When creating the lab it is important that you can test the lab and the reset back to the initial state without loosing changes, that is you should be able to create the lab without having to commit changes to your version control system.

Terminology:

* A lab is a set of files where new text and files appear incrementally for each step. 
* A step is identified by a alphanumberic string that can also include underscore 
* A block of text is started with a line containing `@BEGIN_xxx <step>` and ending with line containing `@END_BLOCK`

A lab user can:

* begin the lab, which sets the current step to the first step and only keeps the blocks that should be visible at this step
* move to the next step (hide and show new blocks)
* move to a specific step
* reset the lab to the initial state before it began
* find out the current step
* get help

A lab creator can:

* add markup to the files
* rearrange the order of the steps in a file called `.labsteps`
* exclude files from the lab in a file called `.labignore`
* define when text should appear in files using markup below
* change the block markup prefix from `@` to something else in a file called `.labconfig`

Markup:

* `@BEGIN_BLOCK <step>` = the block should appear at the specified step and remain in the following steps
* `@BEGIN_BLOCK <stepStep>-<stepEnd>` = the block should only appear at the specified step range
* `@BEGIN_BLOCK <step1>,<step2>,...,<stepN>` = the block should only appear at the steps specified in the list
* `@BEGIN_BLOCK_ONLY <step>` = the block should only appear at the specified step
* `@BEGIN_BLOCK_REMOVE <step>` = the block should disappear at the specified step
* A block is always ended with `@END_BLOCK`
* `@BEGIN_VERSION` is an alias for `@BEGIN_BLOCK`
* `@BEGIN_VERSION_ONLY` is an alias for `@BEGIN_BLOCK_ONY`
* `@BEGIN_UP_TO_VERSION` is an alias for `@BEGIN_BLOCK_REMOVE`
* `@END_VERSION` is an alias for `@END_BLOCK`

Rules:

* A file with no content in a specific step should be removed from that step. For example a file that initially have no content should not exist until it has some content.
* A line containing markup is always completely removed from the output.
* A block of text can contain other blocks of text. The rules for markup processing are applied recursively.
* A block of text must be at the root level or completely contained in single block of text
* By default hidden files (starting with `.`) are ignored.
* Markup is matched ignoring characters before and after the markup. This means that the markup can be embedded within other text. For example `!"#"@END_BLOCK_QWE@` matches `@END_BLOCK`


Examples:

* Valid

    // @BEGIN_BLOCK one
    System.out.println("Hello");
    // @END_BLOCK one
    // @BEGIN_BLOCK two
    System.out.println("world");
    // @END_BLOCK two

* Not valid

    // @BEGIN_BLOCK one
    System.out.println("Hello");
    // @BEGIN_BLOCK two
    // @END_BLOCK one
    System.out.println("world");
    // @END_BLOCK two
    
.labsteps:

* Each line in the file contains a step name 
* The order of the lines defines the order of the steps. Lines at the top of the file are the initial steps.
* Whitespaces are ignored

.labignore:

* Similar to `.gitignore`

.labconfig:

* Should be possible to configure the behavior of the lab, for example the prefix character, default ignore files, aliases
