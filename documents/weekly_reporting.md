# Week 1

A good amount of time was spend on thinking the project idea. After indirectly getting this idea from the list of example assignments I did some prototyping before finally deciding on it. No code has been submitted yet but setting the basic game up shouldn't take too much time and will be up next. After that I can get into the actual 

I had an intuition that the time taken by simple minmax algorithms would be too much and some browsing seemed to support this though. This way I quickly found out the Monte Carlo approach that also seemed much more interesting and supportive of this course's themes. I'm still not completely sure how I will implement the system in practice because I will probably need to juggle between few different presentations of arbitrary game situations for the best possible efficiency.

Time spent this week (rough estimate): 9 hours.


# Week 2

The proximity of the first two deadlines has resulted into not reaching all the milestones this week. The main focus was to reach a stage where two humans can play agains each other while having some basic visual presentation of the game. The current version still requires some bug fixing and general cleaning before it makes sense to start writing javadocs.

JaCoCo took some while to set up due to a lack of practice, but I did manage to figure it out in the end. The main issue was probably going with Gradle in favor of Maven, which I wasn't that proficient with using Netbeans.

Time spent this week: 7 hours.


# Week 3

I'm starting to get the final picture of how the code should be structured. This basically means that a good amount of refactoring is on the way. The AI took less time than anticipated to get into a basic shape. I'm still working on the finer details of how a move should be selected in practise. Some test results also need to be verified before I can consider that part to be "working."

After these two milestones I will shift the focus to implementing the data structures that currently work as wrappers. Working around Java's annoying lack of Generic arrays is not something I'm looking forward to. Creating Javadocs will also be finally sensible as I'm starting to finally settle with my methods.

Time spent this week: 14 hours.


# Week 4

I have sorted out a lot of responsibilities in the code. This has caused some parts to essentially become obsolete. Fixing those will improve the performance and the code in general but it will take some careful consideration when doing so. These changes should eventually lead to more possibilities overall. For example the alpha beta pruning idea was turning out to be impossible because I had separated some functions too wide. However I'm keeping that one as a possible bonus feature for now, as I've yet to even implement the data structures myself.

Time spent this week: 10 hours.
