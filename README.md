# URL Shortener
## Example of how the URL shortener works
1. Given the following URL: https://www.urlshortener.com/q=someinfomation&s=moreinformation&&v=v5&&g=extrainformation.
The service creates an alias with shorter length: https://shorturl.com/y28k-l3l4. If you click the alias, it redirects you to the original URL.

2. The alias is a combination of numbers (0-9) and characters (a-z,A-Z).

3. The URLs can not be updated or updated.

## Development considerations
### Use Cases
There are two use cases, as you can see in the image below:
- Creation of the short url given a long url
- Recovery of the long url given a short url

<img src="https://github.com/lumaivzqz/url-shortener/assets/42591425/449a71bf-43dd-4e53-959f-6ffeac1117f7" alt="drawing" width="50%"/>

### Key-Value database or Relational database?
<img src="(https://github.com/lumaivzqz/url-shortener/assets/42591425/d8d38389-68f9-4ad1-9666-090f4317da6c" alt="drawing" width="50%"/>

For this problem, data consistency and availability is really important. Taking into account the CAP theorem, a SQL database could be taken into consideration.
A key-value database do not ensure consistency or availability (depends on the solution), but allows for data scalability. However, they are usually more expensive than relational databases.

For this solution, a relational database has been chosen to ensure data availability and consistency, and to economize the solution.

### Creation of the short url
For this use case it's needed a Hash function fx that maps a long url to the hash value, as shown in the image below:

<img src="https://github.com/lumaivzqz/url-shortener/assets/42591425/e5cb62c9-d2e4-4468-b11c-6bb3d81164e3" alt="drawing" width="50%"/>

The hash function must satisfy the following requirements:
- each long url must be hashed to one hash value
- each hash value can be mapped back to the original long url

1. The service checks if the long-url is already in the DB
2. If it is, that means the long-url was converted to short-url before. Then, fetch the short-url and returns it.
3. if it is not, that means the long-url is new! The service generates a new ID using a unique ID generator.
4. Then, it converts the ID to short-url using base64 conversion.
5. Finally, it creates a new database row with the ID, the short and long url, and returns the short-url to the client.

<img src="https://github.com/lumaivzqz/url-shortener/assets/42591425/c17bb0ce-ef25-47f3-abd8-5293d5aedaf2" alt="drawing" width="36%"/>

*Note: base64 conversion has been used for simplicity, but in reality there are other hash functions that could be used. Also, the long-url can be used as input to the hash function by itself, but it is important to note that each long-url must be converted to a unique hash value: you must work with collision solvers.*

### Recovery of the long url
For this use case, there is a cache to return more quickly the most used urls.
1. The client click a short-url link.
2. The service receives the GET request.
3. If the short-url is already in the cache, then returns the long-url directly.
4. If it is not there, then fetch it from the DB. If it is not in the DB neither, then returns a BAD REQUEST: it seems the client has entered an invalid short-url.
5. Finally, return the long-url to the client.
   
<img src="https://github.com/lumaivzqz/url-shortener/assets/42591425/10971f6d-8f3a-47f6-8a29-8c27e5995775" alt="drawing" width="60%"/>




