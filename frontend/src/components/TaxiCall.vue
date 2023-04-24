<template>

    <v-card style="width:450px;" outlined>
        <template slot="progress">
            <v-progress-linear
                    color="deep-purple"
                    height="10"
                    indeterminate
            ></v-progress-linear>
        </template>

        <v-card-title v-if="value._links">
            TaxiCall # {{value._links.self.href.split("/")[value._links.self.href.split("/").length - 1]}}
        </v-card-title >
        <v-card-title v-else>
            TaxiCall
        </v-card-title >

        <v-card-text>
            <String label="Number" v-model="value.number" :editMode="editMode"/>
            <String label="Model" v-model="value.model" :editMode="editMode"/>
            <String label="DriverName" v-model="value.driverName" :editMode="editMode"/>
            <String label="DriverMobileNumber" v-model="value.driverMobileNumber" :editMode="editMode"/>
            <Number label="CallId" v-model="value.callId" :editMode="editMode"/>
            <String label="Status" v-model="value.status" :editMode="editMode"/>
            <String label="UserId" v-model="value.userId" :editMode="editMode"/>
            <Number label="Pay" v-model="value.pay" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="edit"
                    v-if="!editMode"
            >
                Edit
            </v-btn>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="save"
                    v-else
            >
                Save
            </v-btn>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="remove"
                    v-if="!editMode"
            >
                Delete
            </v-btn>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="editMode = false"
                    v-if="editMode && !isNew"
            >
                Cancel
            </v-btn>
        </v-card-actions>
        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    v-if="!editMode"
                    color="deep-purple lighten-2"
                    text
                    @click="openBoarding"
            >
                Boarding
            </v-btn>
            <v-dialog v-model="boardingDiagram" width="500">
                <BoardingCommand
                        @closeDialog="closeBoarding"
                        @boarding="boarding"
                ></BoardingCommand>
            </v-dialog>
            <v-btn
                    v-if="!editMode"
                    color="deep-purple lighten-2"
                    text
                    @click="openArrival"
            >
                Arrival
            </v-btn>
            <v-dialog v-model="arrivalDiagram" width="500">
                <ArrivalCommand
                        @closeDialog="closeArrival"
                        @arrival="arrival"
                ></ArrivalCommand>
            </v-dialog>
            <v-btn
                    v-if="!editMode"
                    color="deep-purple lighten-2"
                    text
                    @click="openPaymentRequest"
            >
                PaymentRequest
            </v-btn>
            <v-dialog v-model="paymentRequestDiagram" width="500">
                <PaymentRequestCommand
                        @closeDialog="closePaymentRequest"
                        @paymentRequest="paymentRequest"
                ></PaymentRequestCommand>
            </v-dialog>
            <v-btn
                    v-if="!editMode"
                    color="deep-purple lighten-2"
                    text
                    @click="openAccept"
            >
                Accept
            </v-btn>
            <v-dialog v-model="acceptDiagram" width="500">
                <AcceptCommand
                        @closeDialog="closeAccept"
                        @accept="accept"
                ></AcceptCommand>
            </v-dialog>
            <v-btn
                    v-if="!editMode"
                    color="deep-purple lighten-2"
                    text
                    @click="openDeparture"
            >
                Departure
            </v-btn>
            <v-dialog v-model="departureDiagram" width="500">
                <DepartureCommand
                        @closeDialog="closeDeparture"
                        @departure="departure"
                ></DepartureCommand>
            </v-dialog>
        </v-card-actions>

        <v-snackbar
                v-model="snackbar.status"
                :top="true"
                :timeout="snackbar.timeout"
                color="error"
        >
            {{ snackbar.text }}
            <v-btn dark text @click="snackbar.status = false">
                Close
            </v-btn>
        </v-snackbar>
    </v-card>

</template>

<script>
    const axios = require('axios').default;


    export default {
        name: 'TaxiCall',
        components:{
        },
        props: {
            value: [Object, String, Number, Boolean, Array],
            editMode: Boolean,
            isNew: Boolean,
            offline: Boolean,
        },
        data: () => ({
            snackbar: {
                status: false,
                timeout: 5000,
                text: ''
            },
            boardingDiagram: false,
            arrivalDiagram: false,
            paymentRequestDiagram: false,
            acceptDiagram: false,
            departureDiagram: false,
        }),
        computed:{
        },
        methods: {
            selectFile(){
                if(this.editMode == false) {
                    return false;
                }
                var me = this
                var input = document.createElement("input");
                input.type = "file";
                input.accept = "image/*";
                input.id = "uploadInput";
                
                input.click();
                input.onchange = function (event) {
                    var file = event.target.files[0]
                    var reader = new FileReader();

                    reader.onload = function () {
                        var result = reader.result;
                        me.imageUrl = result;
                        me.value.photo = result;
                        
                    };
                    reader.readAsDataURL( file );
                };
            },
            edit() {
                this.editMode = true;
            },
            async save(){
                try {
                    var temp = null;

                    if(!this.offline) {
                        if(this.isNew) {
                            temp = await axios.post(axios.fixUrl('/taxiCalls'), this.value)
                        } else {
                            temp = await axios.put(axios.fixUrl(this.value._links.self.href), this.value)
                        }
                    }

                    if(this.value!=null) {
                        for(var k in temp.data) this.value[k]=temp.data[k];
                    } else {
                        this.value = temp.data;
                    }

                    this.editMode = false;
                    this.$emit('input', this.value);

                    if (this.isNew) {
                        this.$emit('add', this.value);
                    } else {
                        this.$emit('edit', this.value);
                    }

                    location.reload()

                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
                
            },
            async remove(){
                try {
                    if (!this.offline) {
                        await axios.delete(axios.fixUrl(this.value._links.self.href))
                    }

                    this.editMode = false;
                    this.isDeleted = true;

                    this.$emit('input', this.value);
                    this.$emit('delete', this.value);

                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
            },
            change(){
                this.$emit('input', this.value);
            },
            async boarding(params) {
                try {
                    if(!this.offline) {
                        var temp = await axios.put(axios.fixUrl(this.value._links['택시탑승'].href), params)
                        for(var k in temp.data) {
                            this.value[k]=temp.data[k];
                        }
                    }

                    this.editMode = false;
                    this.closeBoarding();
                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
            },
            openBoarding() {
                this.boardingDiagram = true;
            },
            closeBoarding() {
                this.boardingDiagram = false;
            },
            async arrival(params) {
                try {
                    if(!this.offline) {
                        var temp = await axios.put(axios.fixUrl(this.value._links['목적지도착'].href), params)
                        for(var k in temp.data) {
                            this.value[k]=temp.data[k];
                        }
                    }

                    this.editMode = false;
                    this.closeArrival();
                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
            },
            openArrival() {
                this.arrivalDiagram = true;
            },
            closeArrival() {
                this.arrivalDiagram = false;
            },
            async paymentRequest(params) {
                try {
                    if(!this.offline) {
                        var temp = await axios.put(axios.fixUrl(this.value._links['paymentrequest'].href), params)
                        for(var k in temp.data) {
                            this.value[k]=temp.data[k];
                        }
                    }

                    this.editMode = false;
                    this.closePaymentRequest();
                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
            },
            openPaymentRequest() {
                this.paymentRequestDiagram = true;
            },
            closePaymentRequest() {
                this.paymentRequestDiagram = false;
            },
            async accept(params) {
                try {
                    if(!this.offline) {
                        var temp = await axios.put(axios.fixUrl(this.value._links['accept'].href), params)
                        for(var k in temp.data) {
                            this.value[k]=temp.data[k];
                        }
                    }

                    this.editMode = false;
                    this.closeAccept();
                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
            },
            openAccept() {
                this.acceptDiagram = true;
            },
            closeAccept() {
                this.acceptDiagram = false;
            },
            async departure(params) {
                try {
                    if(!this.offline) {
                        var temp = await axios.put(axios.fixUrl(this.value._links['출발'].href), params)
                        for(var k in temp.data) {
                            this.value[k]=temp.data[k];
                        }
                    }

                    this.editMode = false;
                    this.closeDeparture();
                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
            },
            openDeparture() {
                this.departureDiagram = true;
            },
            closeDeparture() {
                this.departureDiagram = false;
            },
            async () {
                try {
                    if(!this.offline) {
                        var temp = await axios.put(axios.fixUrl(this.value._links[''].href))
                        for(var k in temp.data) {
                            this.value[k]=temp.data[k];
                        }
                    }

                    this.editMode = false;
                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
            },
            async () {
                try {
                    if(!this.offline) {
                        var temp = await axios.put(axios.fixUrl(this.value._links[''].href))
                        for(var k in temp.data) {
                            this.value[k]=temp.data[k];
                        }
                    }

                    this.editMode = false;
                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
            },
            async () {
                try {
                    if(!this.offline) {
                        var temp = await axios.put(axios.fixUrl(this.value._links[''].href))
                        for(var k in temp.data) {
                            this.value[k]=temp.data[k];
                        }
                    }

                    this.editMode = false;
                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
            },
            async () {
                try {
                    if(!this.offline) {
                        var temp = await axios.put(axios.fixUrl(this.value._links[''].href))
                        for(var k in temp.data) {
                            this.value[k]=temp.data[k];
                        }
                    }

                    this.editMode = false;
                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
            },
        },
    }
</script>

