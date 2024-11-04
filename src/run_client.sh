CLIENT_IMAGE='client-image'
PROJECT_NETWORK='project2-bsds'
SERVER_CONTAINER='server-container'

if [ $# -ne 2 ]
then
  echo "Usage: ./run_client.sh <client-id> <port-number>"
  exit
fi

CLIENT_CONTAINER="client-container-$1"

winpty docker run -it --rm --name $CLIENT_CONTAINER \
 --network $PROJECT_NETWORK $CLIENT_IMAGE \
 java client.RMIClientApp $SERVER_CONTAINER "$2"