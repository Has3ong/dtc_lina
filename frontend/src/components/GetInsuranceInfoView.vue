<template>

    <v-data-table
        :headers="headers"
        :items="getInsuranceInfo"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'GetInsuranceInfoView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
            ],
            getInsuranceInfo : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/getInsuranceInfos'))

            temp.data._embedded.getInsuranceInfos.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.getInsuranceInfo = temp.data._embedded.getInsuranceInfos;
        },
        methods: {
        }
    }
</script>

