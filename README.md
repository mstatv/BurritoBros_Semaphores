# BurritoBros Semaphores
University Project - Concurrency Through the Use of Semaphores
---------------------------------------------
## Table of contents
* [Scope of Project](#scope-of-project)
* [Setup](#setup)

## Scope of Project
Consider a small take-out food restaurant called Burrito Brothersthat is open 24 hours, seven days a week.  This very popular establishment offers a VERY-TASTY burrito. There are three servers that own a private beef area, a cheese area, and a tortilla area (IE. Each server has their own ingredients in an infinite supply).  Additionally,there are three shared counter locations, a shared cash register and a shared waiting area that can accommodate up to 15 customers. By law the shop has a maximum customer capacity of 15. A customer cannot enter the shop if it is filled to capacity.  If the there is room a customer will gain access to the shop.  Each customer will enter theshop with an order of one to 25burritos.  As soon as a server is free, the customer that has the shortest order is served next. A server is either servicing a customer or waiting.  Each server will make (at most) three burritos at time for a given customer.   Once a server has obtained all ingredients, a burrito can be made.  When a customer’s entire order is finished, the customer pays a cashier and leaves the shop.  Since there is only one cash register, only one patron may pay at a time. However, in the event that a customer’s entire order has not been filled by the server at the completion of the current counter visit the customer must reenter the waiting area.  The waiting area is organized by the shortest order next.
	
## Setup
To run this project, save repo to computer.

Then:
```
% cd ../bbsemaphore
% javac bbsemaphore/BurritoBros.java 
% java bbsemaphore.BurritoBros.java
```
