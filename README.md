# Java RMI Coursework Template

This is a **template** repository that you can use for creating stages 1 and 2 of the SCC311 coursework with Docker. To act as an example use, the factorize example from moodle is implemented in this repository.

## Why?

This template makes it easy to start building your coursework with source control and docker, all you need is a GitHub account and Docker installed. Because this uses docker, you don't need any Java versions installed on you machine and you can easily build and run all components of the work (including the registry).

> This has only been tested on macos so far, feel free to create issues or contact a TA if you have any problems.

---

## Using Template Repositories

  - Click the `Use this template` button on GitHib
  - When creating your version of the repository, **make sure it is a private repo**! 🔒
  - Clone your newly created repository onto you machine (or use codespaces)
  - 🎉 And its done

Optionally, you can also set up the github actions CI/CD pipeline to automatically build your docker container images and push them to the github container registry. You should probably also change what is in this `README` file to something more relevant.

---

## Getting Started

The code is both compiled and executed within the docker containers, so for both actions you'll need to use the docker CLI.

If you're unfamiliar with docker, it is a complete toolset for creating process in namespaces separate to that of your normal processes. Because of this, each container needs to have all the software needed to build and run the process you wish to containerize. 

For this work, the software you want to containerize are your Java RMI programs. For these, the main dependency is `java`, specifically the `jdk` to be able to compile your code and the `jre` to be able to run your code.

As your RMI server and client should be seperate

### The Server

The `Dockerfile` for the server can be found in the `server` directory. This file defines how the container image should be constructed. To build the image, just run the following command from the root directory of this repository:

```bash
docker build --rm -f server/Dockerfile -t scc311/server:latest .
```

It is in this `docker build` process that your code is compiled with `javac`. Because of this you will have to rebuild the container each time you edit your code. 

With the container image built, you can run your code in a docker container instance with the following command:

```bash
docker run --rm -it --network host --name rmiserver scc311/server:latest
```

This also creates an RMI registry so there is no need to run a separate one!

> `--network host` is used here to remove any complexities involved in docker networking, feel free to remove if you know what you're doing.

### The Client

This is much the same as the server, however the code comes from the `client` and `iface` packages.

To build just run the following:
```bash
docker build --rm -f client/Dockerfile -t scc311/client:latest .
```

And to run:
```bash
docker run --rm -it --network host --name rmiclient scc311/client:latest 10
```
> The `10` here is just the number to pass to the CLI so it can be factorized by the example code.

---

## Prebuilt Images

If you wish to just test running the contents of this repository, the containers are built and published via github actions. You can run a factorize example server easily by using the `ghcr.io/scc311/factorize-server:latest` and a client with the image `ghcr.io/scc311/factorize-client:latest`.
