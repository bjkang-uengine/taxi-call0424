<template>

    <v-card outlined>
        <v-card-title>
            DriverComment
        </v-card-title>

        <v-card-text>
            <Number label="TaxiId" v-model="value.taxiId" :editMode="editMode"/>
            <String label="DriverComment" v-model="value.driverComment" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="driverComment"
            >
                DriverComment
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
        name: 'DriverCommentCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.taxiId = 0;
            this.value.driverComment = '';
        },
        watch: {
        },
        methods: {
            driverComment() {
                this.$emit('driverComment', this.value);
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

