<template>

    <v-card outlined>
        <v-card-title>
            GetReservationInfo # {{$route.params.id }}
        </v-card-title>

        <v-card-text>
            <div>
                <Number label="ReservId" v-model="item.reservId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="AirPlaneId" v-model="item.airPlaneId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="ReservStatus" v-model="item.reservStatus" :editMode="editMode" @change="change" />
            </div>
        </v-card-text>
    </v-card>

</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'GetReservationInfoViewDetail',
    props: {
      value: Object,
    },
    data: () => ({
        item : [],
    }),
    async created() {
      var params = this.$route.params;
      var temp = await axios.get(axios.fixUrl('/getReservationInfos/' + params.id))

      this.item = temp.data;

    },
    methods: {
    }
  }
</script>

