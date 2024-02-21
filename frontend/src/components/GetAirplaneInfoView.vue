<template>

    <v-data-table
        :headers="headers"
        :items="getAirplaneInfo"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'GetAirplaneInfoView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
            ],
            getAirplaneInfo : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/getAirplaneInfos'))

            temp.data._embedded.getAirplaneInfos.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.getAirplaneInfo = temp.data._embedded.getAirplaneInfos;
        },
        methods: {
        }
    }
</script>

