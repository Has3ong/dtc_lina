<template>

    <v-data-table
        :headers="headers"
        :items="getInsuranceFormInfo"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'GetInsuranceFormInfoView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
            ],
            getInsuranceFormInfo : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/getInsuranceFormInfos'))

            temp.data._embedded.getInsuranceFormInfos.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.getInsuranceFormInfo = temp.data._embedded.getInsuranceFormInfos;
        },
        methods: {
        }
    }
</script>

