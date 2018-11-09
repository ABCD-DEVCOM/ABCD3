# ABCD3
New generation ABCD using J-ISIS and PostgreSQL

In order to drop limits posed by the 'traditional' ISIS technology (as implemented e.g. in CISIS of BIREME), 
the third generation of ABCD is using J-ISIS, which uses Berkeley DB as a storage engine and LUCENE for indexing,.
Otherwise the main principles of ISIS (FDT, FST, PFT) are still used as well as the concepts of flexibility (allowing any 
bibiographic standard to be used along with other database-structures) and 'Swiss Knife' multi-purpose ideas.
ABCD 3.0 is a hybrid system, using PostgreSQL for the management of administrative data in the library system (users, 
permissions, profiles, loanobjects, circulation rules).

The software has been developed - and is still being developed - by a Cuban team working at the Universidad de Ciencias Informáticas
(UCI) and is now used at all universities and higher education institutions in the country. Currently also the institutions of 
the Ministry of Education (including secundary schools and research institutions) are installing the software, so there is already
a larger user-base with lots of experiences.

The ABCD 3.0 package includes J-ISIS, Virgo-server and a PostgreSQL-database backup. Java needs to be available on the server
Currently only Linux is supported while in principle all software will also run in Windows.
