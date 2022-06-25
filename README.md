# agent

### Install Go

Install Go on your operating system distribution form link
```sh
https://go.dev/dl/
```

Check the installed Go version command on the command line
```sh
go version
```

If using Windows system and the command isn't available, add bin folder to Enviroment Variable from the install folder with Go

### Configure Project
Edit environment variable for changing how Go imports packages
```sh
go env -w GO111MODULE=on
```

Configure packages, go to the /agent/src folder and run the commands
```sh
go mod init move2.it/agent
go mod tidy
```

If the go.mod file is empty or go.sum doesn't exist, install the used libraries in the project
```sh
go get github.com/gin-gonic/gin
go get github.com/stretchr/testify
```

### Running the project
Go to location agent\src and start the server with the command
```sh
go run .\main.go
```

### Create binary file
Go to location agent\src and build the server with the command
```sh
go build .\main.go
```

### Running unit tests
Go to location agent\src\test and run the test with the command
```sh
go test ./... -cover
```
