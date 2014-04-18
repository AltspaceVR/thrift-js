# thrift-js

This is a stripped-down version of the thrift js library installable
standalone and containing a number of performance optimisations.

Its primary purpose is to support the Ably browser client library and therefore
it excludes support for:

- RPC;
- any transport other than TTransport;
- any protocol other than TBinaryProtocol.

This library can be used to
 - Deserialize thrift binary byte array into thrift object and vice versa in javascript in browser
 - Make RPC call to remote thrift server

## Examples

### Deserialization from thrift binary array buffer to thrift object 

Consider the following thrift struct.

    struct User {
       1: optional i64 id = 0;
       2: optional string name = "unknown";
       3: optional string email = "unknown";
    }
    
Generated js code as `thrift --gen js user.thrift`

**Note:** I used thrift-0.9 to generate the js file but the code in this repo is for thrift 0.8. Hence I had to substitute all the `.value` field by empty string in the generated javascript. So I did: `sed -i '' 's/.value//g' gen-js/user_types.js`. This may not be necessary in case you use thrift-0.8 to generate the javascript files.

Include the following in the html page in the same order:


    <script type="text/javascript" src="js/thrift-js/util.js"></script>
    <script type="text/javascript" src="js/thrift-js/thrift.js"></script>
    <script type="text/javascript" src="js/thrift-js/buffer.js"></script>
    <script type="text/javascript" src="js/thrift-js/transport.js"></script>
    <script type="text/javascript" src="js/thrift-js/int64.js"></script>
    <script type="text/javascript" src="js/thrift-js/protocol.js"></script>
    <script type="text/javascript" src="js/gen-js/user_types.js"></script>


Following javascript would decode the binary object decoded as base64 string:

    var base64Str = "CAABAAAAKgsAAgAAAANCb2ILAAMAAAAPYm9iQGV4YW1wbGUuY29tAA=="
    var thriftData = b64toUint8Array(base64Str)

    var buffer = new Buffer(thriftData.length);
    buffer.view = new DataView(thriftData)

    var transport = new TFramedTransport(buffer);
    var protocol = new TBinaryProtocol(transport);
    var user = new User();

    user.read(protocol);
    alert("Parsed successfully! \n ssn = " + user.ssn + ", name = " + user.name + ", email = " + user.email)

See examples in the example directory. Note that the data need not be in base64 format. The binary array in this example was generated using scala. See the code in **gen-thrift-obj-in-scala** for the scala code that generated the base64 string. The java code for User was generated using `thrift --gen java user.thrift`

