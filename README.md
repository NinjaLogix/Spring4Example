Spring Regime Serverside
-----------------------------------------------------------------------------------------------------------------------
changelog - Added in named querires and removed generics
- removed generics because as this is a nice way to make your hibernate class flexible for multiple ojects, it's a
    bit overkill for this example where we are only dealing with one hibernate object.
- moved all logging to aop classes except for config class

Branch Description
-----------------------------------------------------------------------------------------------------------------------
3.2.2-Named_Queries
This branch covers the addittion of named queries. I removed the non named query stuff and added named queries to show 
the difference between using hibernate with/without named queries. I also moved around the aop stuff to better segment
each usage. Later I'll add more for each method of the corresponding aop class for better logging purposes, but for now
it's fine where it is for learning purposes. I also removed some of the generics from the HibernateUtil class as it was
a bit overkill for this example, but it is a very good practice for this because multiple classes can use the same basic
crud operations.