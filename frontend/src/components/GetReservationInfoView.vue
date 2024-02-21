<template>

    <v-data-table
        :headers="headers"
        :items="getReservationInfo"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'GetReservationInfoView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "reservId", value: "reservId" },
                { text: "airPlaneId", value: "airPlaneId" },
                { text: "reservStatus", value: "reservStatus" },
            ],
            getReservationInfo : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/getReservationInfos'))

            temp.data._embedded.getReservationInfos.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.getReservationInfo = temp.data._embedded.getReservationInfos;
        },
        methods: {
        }
    }
</script>

