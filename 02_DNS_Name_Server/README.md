# DNS (Domain Name Server) Program

## Overview
DNS lookup program that converts hostnames to IP addresses (forward lookup) and IP addresses to hostnames (reverse lookup) using Java's InetAddress class.

## Files
- **DNS.java** - Single file with all DNS functionality

## Code Explanation

### Key Class: InetAddress
```java
InetAddress.getByName(hostname)  → Returns InetAddress for hostname
addr.getHostAddress()            → Returns IP address as string
addr.getHostName()               → Returns hostname as string
```

### Program Flow
1. User selects forward or reverse lookup
2. Enters hostname or IP address
3. InetAddress resolves and returns result

## How to Run
```bash
javac DNS.java
java DNS
```

---

## Viva Questions

### Basic Questions
1. **What is DNS?**
   - Domain Name System - translates human-readable domain names (google.com) to IP addresses (142.250.190.14).

2. **What is forward DNS lookup?**
   - Converting hostname to IP address (google.com → 142.250.190.14)

3. **What is reverse DNS lookup?**
   - Converting IP address to hostname (142.250.190.14 → google.com)

4. **What class is used for DNS in Java?**
   - java.net.InetAddress

5. **What method converts hostname to IP?**
   - InetAddress.getByName(hostname).getHostAddress()

### Intermediate Questions
6. **What is the difference between getByName() and getAllByName()?**
   - getByName(): Returns first IP for hostname
   - getAllByName(): Returns all IPs (for load-balanced servers)

7. **What happens if hostname doesn't exist?**
   - Throws UnknownHostException

8. **What is localhost and its IP?**
   - localhost refers to current machine, IP is 127.0.0.1

9. **What is a DNS server?**
   - A server that stores DNS records and responds to DNS queries.

10. **What port does DNS use?**
    - Port 53 (UDP for queries, TCP for zone transfers)

### Advanced Questions
11. **Explain DNS hierarchy.**
    - Root servers → TLD servers (.com, .org) → Authoritative servers → Local DNS resolver

12. **What is DNS caching?**
    - Storing DNS responses temporarily to reduce lookup time and network traffic.

13. **What is TTL in DNS?**
    - Time To Live - how long a DNS record should be cached.

14. **What are DNS record types?**
    - A (IPv4), AAAA (IPv6), CNAME (alias), MX (mail), NS (nameserver), PTR (reverse)

15. **What is DNS poisoning?**
    - Attack where false DNS records are inserted into cache, redirecting users to malicious sites.

16. **Difference between iterative and recursive DNS query?**
    - Iterative: DNS server returns best answer or referral
    - Recursive: DNS server does complete resolution and returns final answer
