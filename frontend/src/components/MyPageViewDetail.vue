<template>

    <v-card outlined>
        <v-card-title>
            MyPage # {{$route.params.id }}
        </v-card-title>

        <v-card-text>
            <div>
                <Number label="InsuranceId" v-model="item.insuranceId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="UsrId" v-model="item.usrId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="PrdId" v-model="item.prdId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="UsrNm" v-model="item.usrNm" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="PrdNm" v-model="item.prdNm" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="PrdStatus" v-model="item.prdStatus" :editMode="editMode" @change="change" />
            </div>
        </v-card-text>
    </v-card>

</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'MyPageViewDetail',
    props: {
      value: Object,
    },
    data: () => ({
        item : [],
    }),
    async created() {
      var params = this.$route.params;
      var temp = await axios.get(axios.fixUrl('/myPages/' + params.id))

      this.item = temp.data;

    },
    methods: {
    }
  }
</script>

