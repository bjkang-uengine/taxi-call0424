<template>

    <v-card outlined>
        <v-card-title>
            PaymentRequest
        </v-card-title>

        <v-card-text>
            <Number label="CalliId" v-model="value.calliId" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="paymentRequest"
            >
                PaymentRequest
            </v-btn>
            
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="close"
            >
                Close
            </v-btn>
        </v-card-actions>
    </v-card>

</template>

<script>
   
    export default {
        name: 'PaymentRequestCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.calliId = 0;
        },
        watch: {
        },
        methods: {
            paymentRequest() {
                this.$emit('paymentRequest', this.value);
            },
            close() {
                this.$emit('closeDialog');
            },
            change() {
                this.$emit('input', this.value);
            },
        }
    }
</script>

